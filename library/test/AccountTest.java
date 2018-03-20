import Account.Account;

import MinimumBalanceException.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws MinimumBalanceException {
       account = new Account("1234", 5000);
    }

    @Test
    public void checkBalance(){
        assertThat(account.getBalance(),is(5000));
    }

    @Test
    public void checkAccountNumber(){
        assertThat(account.getAccountNumber(),is("1234"));
    }

    @Test(expected= MinimumBalanceException.class)
    public void checkMinimumBalanceException() throws MinimumBalanceException{
        new Account("2345",200);
    }
    @Test
    public void addMoneyIntoTheAccount (){
        account.creditMoney(1000);
        assertThat(account.getBalance(),is(6000));
    }

}
