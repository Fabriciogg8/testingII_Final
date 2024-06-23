package para_bank.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.para_bank.pages.AccountsOverviewPage;

public class AccountsOverviewTest extends BaseTest {

    @Test
    public void testAccountsOverview() {
        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver);
        accountsOverviewPage.openAccountsOverview();
        String balanceText = accountsOverviewPage.getBalanceText();
        Assertions.assertTrue(balanceText.contains("*Balance includes deposits that may be subject to holds"));
    }
}
