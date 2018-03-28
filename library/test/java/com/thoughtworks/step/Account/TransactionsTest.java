package com.thoughtworks.step.Account;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
public class TransactionsTest {

    private Transactions transactions;
    private SimpleDateFormat simpleDateFormatter;

    @BeforeEach
    void setUp() {
        simpleDateFormatter = new SimpleDateFormat("yyyy-mm-dd");
        transactions = new Transactions();
    }


    @Before

    @Test
    void mustRecordCorrectDebitTransaction() {
        transactions.debit(1000, "Dhanashri");
        assertThat(transactions.list, hasItem(new DebitTransaction(new Date(), 1000, "Dhanashri")));
    }

    @Test
    void mustRecordCorrectCreditTransaction() {
        transactions.credit(2000, "Madhuri");
        assertThat(transactions.list, hasItem(new CreditTransaction(new Date(), 2000, "Madhuri")));
    }

    @Test
    void mustRecordAllDebitTransactions() {
        transactions.debit(1000, "Aditi");
        transactions.debit(1000, "Aditi");
        DebitTransaction aditi = new DebitTransaction(new Date(), 1000, "Aditi");
        DebitTransaction adu = new DebitTransaction(new Date(), 1000, "Aditi");
        assertThat(transactions.list, hasItems(aditi, adu));

    }

    @Test
    void mustRecordBothCreditAndDebitTransactions() {
        transactions.credit(1000, "Sayima");
        transactions.debit(500, "Sayima");
        CreditTransaction sayu = new CreditTransaction(new Date(), 1000, "Sayima");
        DebitTransaction sayima = new DebitTransaction(new Date(), 500, "Sayima");
        assertThat(transactions.list, hasItems(sayu, sayima));
    }

    @Test
    void shouldFilterTransactionByAmountGreaterThan() {
        transactions.credit(1000, "Pallabi");
        transactions.credit(400, "Dhanu");
        transactions.credit(1500, "Pragya");
        transactions.debit(500, "Ishu");
        Transactions filteredTransactions = transactions.filterByAmountGreaterThan(500);
        CreditTransaction pallabi = new CreditTransaction(new Date(), 1000, "Pallabi");
        CreditTransaction pragya = new CreditTransaction(new Date(), 1500, "Pragya");
        DebitTransaction ishu = new DebitTransaction(new Date(), 500, "Ishu");
        assertThat(filteredTransactions.list, hasItems(pallabi, pragya, ishu));
    }

    @Test
    void shouldFilterTransactionByAmountLesserThan() {
        transactions.credit(500, "Aditi");
        transactions.credit(700, "Ashish");
        transactions.credit(1000, "Shubham");
        transactions.debit(500, "Sayima");
        Transactions filteredTransactions = this.transactions.filterByAmountLesserThan(900);
        CreditTransaction aditi = new CreditTransaction(new Date(), 500, "Aditi");
        CreditTransaction shubham = new CreditTransaction(new Date(), 700, "Ashish");
        DebitTransaction sayu = new DebitTransaction(new Date(), 500, "Sayima");
        assertThat(filteredTransactions.list, hasItems(aditi, shubham, sayu));
    }

    @Test
    void shouldFilterTransactionByCreditType() {
        transactions.credit(700, "Ashish");
        transactions.credit(1000, "Shubham");
        transactions.debit(500, "Sayima");
        Transactions filteredTransactions = this.transactions.filterAllCreditTransaction();
        CreditTransaction shubham = new CreditTransaction(new Date(), 1000, "Shubham");
        CreditTransaction ashish = new CreditTransaction(new Date(), 700, "Ashish");
        assertThat(filteredTransactions.list, hasItems(shubham, ashish));
    }

    @Test
    void shouldFilterTransactionByDebitType() {
        transactions.credit(700, "Ashish");
        transactions.credit(1000, "Shubham");
        transactions.debit(500, "Sayima");
        Transactions filteredTransactions = this.transactions.filterAllDebitTransaction();
        DebitTransaction sayima = new DebitTransaction(new Date(), 500, "Sayima");
        assertThat(filteredTransactions.list, hasItems(sayima));
    }

    @Test
    @Ignore
    void shouldFilterTransactionsBySpecificDate() {

    }

    @Test
    void shouldPrintTransaction() throws FileNotFoundException, UnsupportedEncodingException {
        CreditTransaction sree = new CreditTransaction(new Date(),1000, "Sreenadh");
        ArrayList<String> result = new ArrayList<>();
        transactions.credit(1000, "Sreenadh");
        PrintWriter printWriter = new PrintWriter("the-file-name.txt", "UTF-8") {
            @Override
            public void println(String x) {
                result.add(x);
            }
        };
        transactions.print(printWriter);
        printWriter.close();
        assertThat(result, hasItems(sree.toString()));
    }

    @Test
    void shouldPrintTrasactionInCsvFormat() throws IOException {
        CreditTransaction dhanu = new CreditTransaction(new Date(),1000, "Dhanu");
        ArrayList<String> expected = new ArrayList<>();
        transactions.credit(1000,"Dhanu");
        FileWriter fileWriter = new FileWriter("sample.txt"){
            @Override
            public void write(String x){
                expected.add(x);
            }
        };
        transactions.writeInCsv(fileWriter);
        fileWriter.flush();
        fileWriter.close();
        assertThat(expected,hasItems(dhanu.toCsv()));
    }

    @Test
    void shouldFilterAllTransactionsOfParticularDate() throws ParseException {
        Date TwentyEightMarch = simpleDateFormatter.parse("2018-03-28");
        Date TwentyFiveMarch = simpleDateFormatter.parse("2018-03-25");
        CreditTransaction aditi = new CreditTransaction(TwentyEightMarch,1000, "Aditi");
        CreditTransaction pragya = new CreditTransaction(TwentyEightMarch, 900, "Pragya");
        CreditTransaction ishu = new CreditTransaction(TwentyFiveMarch, 800, "Ishu");
        transactions.credit(TwentyEightMarch,1000,"Aditi");
        transactions.credit(TwentyEightMarch,900,"Pragya");
        transactions.credit(TwentyFiveMarch,800,"Ishu");
        Transactions filterTransactions = this.transactions.filterTransactionsBySpecificDate(TwentyEightMarch);
        assertThat(filterTransactions.list,hasItems(aditi,pragya));
    }

    @Test
    void shouldFilterAllTransactionsBeforeSpecificDate() throws ParseException {
        Date TwentyEightMarch = simpleDateFormatter.parse("2018-03-28");
        Date TwentyFourMarch = simpleDateFormatter.parse("2018-03-24");
        Date TwentyFiveMarch = simpleDateFormatter.parse("2018-03-25");
        CreditTransaction omkar = new CreditTransaction(TwentyEightMarch, 1000, "Omkar");
        CreditTransaction ashish = new CreditTransaction(TwentyFiveMarch, 1000, "Ashish");
        DebitTransaction shubham = new DebitTransaction(TwentyFourMarch, 1000, "Shubham");
        transactions.credit(TwentyEightMarch,1000,"Omkar");
        transactions.credit(TwentyFiveMarch,1000,"Ashish");
        transactions.debit(TwentyFourMarch,1000,"Shubham");
        Transactions filterTransactions = this.transactions.filterTransactionsBeforeSpecificDate(TwentyFiveMarch);
        assertThat(filterTransactions.list,hasItems(shubham));
    }

    @Test
    void shouldFilterAllTransactionsAfterSpecificDate() throws ParseException{
        Date TwentyEightMarch = simpleDateFormatter.parse("2018-03-28");
        Date TwentyFourMarch = simpleDateFormatter.parse("2018-03-24");
        Date TwentyFiveMarch = simpleDateFormatter.parse("2018-03-25");
        CreditTransaction omkar = new CreditTransaction(TwentyEightMarch, 1000, "Omkar");
        CreditTransaction ashish = new CreditTransaction(TwentyFiveMarch, 1000, "Ashish");
        DebitTransaction shubham = new DebitTransaction(TwentyFourMarch, 1000, "Shubham");
        transactions.credit(TwentyEightMarch,1000,"Omkar");
        transactions.credit(TwentyFiveMarch,1000,"Ashish");
        transactions.debit(TwentyFourMarch,1000,"Shubham");
        Transactions filterTransactions = this.transactions.filterTransactionsAfterSpecificDate(TwentyFiveMarch);
        assertThat(filterTransactions.list,hasItems(omkar));
    }
}


