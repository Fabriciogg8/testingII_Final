package para_bank.tests;

import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.para_bank.pages.TransferFundsPage;
import org.para_bank.utils.ReportFactory;

import java.time.Duration;

public class TransferFundsTest extends BaseTest {

    @Test
    public void testTransferFunds() {
        extentTest = extent.createTest("Transferencia de Fondos");

        TransferFundsPage transferFundsPage = new TransferFundsPage(driver);

        extentTest.log(Status.INFO, "Navegando a la página de transferencia de fondos");
        transferFundsPage.openTransferFunds();
        ReportFactory.takeScreenshot(extentTest, "NavegarPaginaTransferenciaFondos", driver);

        // Esperar a que el texto de la página de transferencia de fondos esté presente
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='showForm']/h1")));
            extentTest.log(Status.INFO, "Página de transferencia de fondos cargada");
            ReportFactory.takeScreenshot(extentTest, "PaginaTransferenciaFondosCargada", driver);

            transferFundsPage.getTransferFundsText();
            extentTest.log(Status.INFO, "Llenando los detalles de la transferencia de fondos");
            transferFundsPage.fillTransferDetails("100");
            ReportFactory.takeScreenshot(extentTest, "DetallesTransferenciaFondosLlenos", driver);

            transferFundsPage.submitTransfer();
            extentTest.log(Status.INFO, "Formulario de transferencia de fondos enviado");
            ReportFactory.takeScreenshot(extentTest, "FormularioTransferenciaFondosEnviado", driver);

            String successMessage = transferFundsPage.getSuccessMessage();
            extentTest.log(Status.INFO, "Mensaje de éxito recibido: " + successMessage);
            Assertions.assertTrue(successMessage.contains("Transfer Complete!"), "El mensaje de éxito esperado no está presente");
            extentTest.log(Status.PASS, "Transferencia de fondos completada exitosamente");
        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Error durante la prueba de transferencia de fondos: " + e.getMessage());
            ReportFactory.takeScreenshot(extentTest, "ErrorTransferenciaFondos", driver);
            Assertions.fail("Error durante la prueba de transferencia de fondos: " + e.getMessage());
        }
    }
}