package para_bank.tests;

import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.para_bank.pages.OpenAccountPage;
import org.para_bank.utils.ReportFactory;

public class OpenAccountTest extends BaseTest {

    @Test
    @Tag("Open Account")
    public void testOpenNewAccount() {
        extentTest = extent.createTest("Apertura de Nueva Cuenta");

        OpenAccountPage openAccountPage = new OpenAccountPage(driver);

        extentTest.log(Status.INFO, "Navegando a la página de Apertura de Cuenta");
        openAccountPage.openNewAccount();
        ReportFactory.takeScreenshot(extentTest, "PaginaAperturaDeCuenta", driver);

        extentTest.log(Status.INFO, "Seleccionando cuenta de ahorros");
        openAccountPage.selectSavingsAccount();
        ReportFactory.takeScreenshot(extentTest, "SeleccionCuentaAhorros", driver);

        extentTest.log(Status.INFO, "Enviando formulario de apertura de cuenta");
        openAccountPage.submitOpenAccount();
        ReportFactory.takeScreenshot(extentTest, "FormularioAperturaEnviado", driver);

        String successMessage = openAccountPage.getSuccessMessage();
        extentTest.log(Status.INFO, "Mensaje de éxito recibido: " + successMessage);
        Assertions.assertTrue(successMessage.contains("Congratulations, your account is now open."));
        extentTest.log(Status.PASS, "Apertura de cuenta completada exitosamente");
    }
}