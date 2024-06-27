package para_bank.tests;

import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.para_bank.pages.AccountsOverviewPage;
import org.para_bank.utils.ReportFactory;

public class AccountsOverviewTest extends BaseTest {

    @Test
    public void testAccountsOverview() {
        extentTest = extent.createTest("Accounts Overview Test");
        AccountsOverviewPage accountsOverviewPage = new AccountsOverviewPage(driver);

        extentTest.log(Status.INFO, "Navegando a la página de Resumen de Cuentas");
        accountsOverviewPage.openAccountsOverview();
        ReportFactory.takeScreenshot(extentTest, "AccountsOverviewPage_Navegar", driver);

        extentTest.log(Status.INFO, "Verificando el texto de saldo");
        String balanceText = accountsOverviewPage.getBalanceText();
        extentTest.log(Status.INFO, "Texto de saldo: " + balanceText);
        ReportFactory.takeScreenshot(extentTest, "AccountsOverviewPage_TextoSaldo", driver);

        Assertions.assertTrue(balanceText.contains("*Balance includes deposits that may be subject to holds"), "El texto de saldo esperado no está presente");
        extentTest.log(Status.PASS, "Texto de saldo verificado correctamente");
    }
}