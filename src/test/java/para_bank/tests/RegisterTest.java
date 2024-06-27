package para_bank.tests;

import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.para_bank.pages.RegisterPage;
import org.para_bank.utils.ReportFactory;

import java.util.Random;

public class RegisterTest extends BaseTest {

    public RegisterTest() {
        // Indicamos que es el test de register para que no haga el login
        isRegisterTest = true;
    }

    @Test
    @Tag("Registro")
    public void testUserRegistration() {
        extentTest = extent.createTest("Registro de Usuario");

        RegisterPage registerPage = new RegisterPage(driver);

        extentTest.log(Status.INFO, "Navegando al formulario de registro");
        registerPage.navigateToRegistrationForm();
        ReportFactory.takeScreenshot(extentTest, "NavegarFormularioRegistro", driver);

        if (registerPage.isRegistrationErrorMessagePresent()) {
            extentTest.log(Status.FAIL, "El registro está actualmente deshabilitado.");
            Assertions.fail("Registration is currently disabled. Please try again later.");
        } else {
            extentTest.log(Status.INFO, "Intentando registrarse con el usuario juandoe");

            // Intentar registrar con el usuario conocido juandoe y contraseña 12345
            try {
                registerPage.fillRegistrationForm("Juan", "Doe", "123 Main St", "Anytown", "CA", "90210", "555-1234", "123-45-6789", "juandoe", "12345");
                registerPage.submitRegistration();
                extentTest.log(Status.INFO, "Formulario de registro enviado con juandoe");
                ReportFactory.takeScreenshot(extentTest, "RegistroJuandoe", driver);

                // Verificar mensaje de éxito esperado
                String successMessage = registerPage.getSuccessMessage();
                extentTest.log(Status.INFO, "Mensaje de éxito recibido: " + successMessage);
                Assertions.assertEquals("Your account was created successfully. You are now logged in.", successMessage);
                extentTest.log(Status.PASS, "Registro completado exitosamente con juandoe");
            } catch (AssertionError e) {
                extentTest.log(Status.WARNING, "Registro con juandoe falló: " + e.getMessage());
                extentTest.log(Status.INFO, "Generando nombre de usuario único para registro aleatorio");

                // Generar un nombre de usuario único usando un número aleatorio
                Random random = new Random();
                String uniqueUsername = "juandoe" + random.nextInt(10000);
                extentTest.log(Status.INFO, "Usando el nombre de usuario aleatorio: " + uniqueUsername);

                // Registrar con el nombre de usuario único aleatorio y contraseña
                registerPage.fillRegistrationForm("Juan", "Doe", "123 Main St", "Anytown", "CA", "90210", "555-1234", "123-45-6789", uniqueUsername, "password");
                ReportFactory.takeScreenshot(extentTest, "FormularioLleno", driver);

                registerPage.submitRegistration();
                extentTest.log(Status.INFO, "Formulario de registro enviado");
                ReportFactory.takeScreenshot(extentTest, "RegistroEnviado", driver);

                // Verificar mensaje de éxito esperado después de registro aleatorio
                String successMessageRandom = registerPage.getSuccessMessage();
                extentTest.log(Status.INFO, "Mensaje de éxito recibido: " + successMessageRandom);
                Assertions.assertEquals("Your account was created successfully. You are now logged in.", successMessageRandom);
                extentTest.log(Status.PASS, "Registro completado exitosamente con nombre de usuario aleatorio");
            }
        }
    }
}