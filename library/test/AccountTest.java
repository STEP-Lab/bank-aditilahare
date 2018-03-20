import Account.Account;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class AccountTest {
    @Test
    public void checkBalance(){
        Account account = new Account("1234", 1000);
        assertThat(account.getBalance(),is(1000));
    }
    @Test
    public void checkAccountNumber(){
        Account account = new Account("1234", 100);
        assertThat(account.getAccountNumber(),is("1234"));
    }
}
