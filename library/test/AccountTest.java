import com.thoughtworks.step.Account.Account;
import com.thoughtworks.step.Account.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws MinimumBalanceException {
       account = new Account("1234", 5000);
    }

    @Test
    public void checkBalance(){
        assertThat(account.getBalance(),is(5000.0));
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
        assertThat(account.getBalance(),is(6000.0));
    }
    @Test
    public void reduceMoneyFromAccount () throws MinimumBalanceException {
        account.debitMoney(1000);
        assertThat(account.getBalance(),is(4000.0));
    }

    @Test
    public void checkMinimumBalanceWhileDebiting() throws MinimumBalanceException {
        Account account = new Account("1234", 3000);
        account.debitMoney(2800);
    }
}
