package com.thoughtworks.step.Account;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
public class TransactionsTest {

    @Test
    void mustRecordCorrectDebitTransaction() {
        Transactions transactions = new Transactions();
        transactions.debit(1000,"Dhanashri");
        assertThat(transactions.list,hasItem(new DebitTransaction(new Date(),1000,"Dhanashri")));
    }
}
