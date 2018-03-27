package com.thoughtworks.step.Account;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
public class TransactionsTest {

    private Transactions transactions;

    @BeforeEach
    void setUp() {
        transactions = new Transactions();
    }

    @Before

    @Test
    void mustRecordCorrectDebitTransaction() {
        transactions.debit(1000,"Dhanashri");
        assertThat(transactions.list,hasItem(new DebitTransaction(new Date(),1000,"Dhanashri")));
    }

    @Test
    void mustRecordCorrectCreditTransaction() {
        transactions.credit(2000,"Madhuri");
        assertThat(transactions.list,hasItem(new CreditTransaction(new Date(),2000,"Madhuri")));
    }

    @Test
    void mustRecordAllDebitTransactions() {
        transactions.debit(1000,"Aditi");
        transactions.debit(1000,"Aditi");
        DebitTransaction aditi = new DebitTransaction(new Date(), 1000, "Aditi");
        DebitTransaction adu = new DebitTransaction(new Date(), 1000, "Aditi");
        assertThat(transactions.list,hasItems(aditi,adu));

    }

    @Test
    void mustRecordBothCreditAndDebitTransactions() {
        transactions.credit(1000,"Sayima");
        transactions.debit(500,"Sayima");
        CreditTransaction sayu = new CreditTransaction(new Date(), 1000, "Sayima");
        DebitTransaction sayima = new DebitTransaction(new Date(), 500, "Sayima");
        assertThat(transactions.list,hasItems(sayu,sayima));
    }

    @Test
    void shouldFilterTransactionByAmountGreaterThan() {
        transactions.credit(1000,"Pallabi");
        transactions.credit(400,"Dhanu");
        transactions.credit(1500,"Pragya");
        transactions.debit(500,"Ishu");
        Transactions filteredTransactions = transactions.filterByAmountGreaterThan(500);
        CreditTransaction pallabi = new CreditTransaction(new Date(), 1000, "Pallabi");
        CreditTransaction pragya = new CreditTransaction(new Date(), 1500, "Pragya");
        DebitTransaction ishu = new DebitTransaction(new Date(), 500, "Ishu");
        assertThat(filteredTransactions.list,hasItems(pallabi,pragya,ishu));
    }

    @Test
    void shouldFilterTransactionByAmountLesserThan() {
        transactions.credit(500,"Aditi");
        transactions.credit(700,"Ashish");
        transactions.credit(1000,"Shubham");
        transactions.debit(500,"Sayima");
        Transactions filteredTransactions = this.transactions.filterByAmountLesserThan(900);
        CreditTransaction aditi = new CreditTransaction(new Date(), 500, "Aditi");
        CreditTransaction shubham = new CreditTransaction(new Date(), 700, "Ashish");
        DebitTransaction sayu = new DebitTransaction(new Date(), 500, "Sayima");
        assertThat(filteredTransactions.list,hasItems(aditi,shubham,sayu));
    }

    @Test
    void shouldFilterTransactionByTypeOfTransaction() {
        transactions.credit(700,"Ashish");
        transactions.credit(1000,"Shubham");
        transactions.debit(500,"Sayima");
        Transactions filteredTransactions = this.transactions.filterByTransactionType("Credit");
        CreditTransaction shubham = new CreditTransaction(new Date(), 1000, "Shubham");
        CreditTransaction ashish = new CreditTransaction(new Date(), 700, "Ashish");
        assertThat(filteredTransactions.list,hasItems(shubham,ashish));
    }
}

