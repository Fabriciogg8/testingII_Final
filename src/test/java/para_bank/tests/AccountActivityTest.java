package para_bank.tests;

import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.para_bank.pages.AccountActivityPage;
import org.para_bank.utils.ReportFactory;

import java.time.Duration;

public class AccountActivityTest extends BaseTest {

    @Test
    public void testAccountActivity() {
        extentTest = extent.createTest("Actividad de Cuenta");

        AccountActivityPage accountActivityPage = new AccountActivityPage(driver);

        extentTest.log(Status.INFO, "Navegando a la página de Resumen de Cuentas");
        accountActivityPage.goToAccountsOverview();
        ReportFactory.takeScreenshot(extentTest, "PaginaResumenDeCuentas", driver);

        extentTest.log(Status.INFO, "Verificando el texto de balance");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(accountActivityPage.getBalanceIncludesTextLocator()));
            Assertions.assertTrue(accountActivityPage.isBalanceIncludesTextPresent(), "*Balance includes deposits that may be subject to holds text not found");
            extentTest.log(Status.PASS, "Texto de balance verificado correctamente");

            extentTest.log(Status.INFO, "Haciendo clic en la primera cuenta");
            wait.until(ExpectedConditions.elementToBeClickable(accountActivityPage.getFirstAccountLinkLocator()));
            accountActivityPage.clickFirstAccount();
            ReportFactory.takeScreenshot(extentTest, "ClicPrimeraCuenta", driver);

            extentTest.log(Status.INFO, "Verificando el título de detalles de la cuenta");
            wait.until(ExpectedConditions.visibilityOfElementLocated(accountActivityPage.getAccountDetailsTitleLocator()));
            Assertions.assertTrue(accountActivityPage.isAccountDetailsTitlePresent(), "Account Details title not found");
            extentTest.log(Status.PASS, "Título de detalles de la cuenta verificado correctamente");

            extentTest.log(Status.INFO, "Seleccionando mes y tipo de transacción");
            accountActivityPage.selectMonth("All");
            accountActivityPage.selectTransactionType("All");
            ReportFactory.takeScreenshot(extentTest, "MesYTipoSeleccionados", driver);

            extentTest.log(Status.INFO, "Haciendo clic en el botón Ir");
            accountActivityPage.clickGo();
            ReportFactory.takeScreenshot(extentTest, "ClicBotonIr", driver);
            extentTest.log(Status.PASS, "Actividad de cuenta verificada correctamente");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Error durante la prueba de actividad de cuenta: " + e.getMessage());
            ReportFactory.takeScreenshot(extentTest, "ErrorActividadDeCuenta", driver);
            Assertions.fail("Error durante la prueba de actividad de cuenta: " + e.getMessage());
        }
    }
}