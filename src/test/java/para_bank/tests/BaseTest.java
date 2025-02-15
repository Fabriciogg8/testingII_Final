package para_bank.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.para_bank.pages.LoginPage;
import org.para_bank.utils.ReportFactory;

public class BaseTest {
    protected WebDriver driver;
    protected boolean isRegisterTest = false;
    static ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
    static ExtentReports extent;
    ExtentTest extentTest;

    @BeforeEach
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        extent = ReportFactory.getInstance();
        extent.attachReporter(spark);

        // Lógica para verificar el tipo de test que se está ejecutando
        if (isRegisterTest) {
            // No realizar login en el test de register
            System.out.println("Skipping login for register test");
            extentTest = extent.createTest("Base_Test");
            extentTest.log(Status.INFO, "Inicia el test register...");
        } else {
            extentTest = extent.createTest("Base_Test");
            extentTest.log(Status.INFO, "Inicia los test...");
            // Realizar login para todos los otros tests
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("juandoe", "12345");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            extent.flush();
            driver.quit();
        }
    }
}