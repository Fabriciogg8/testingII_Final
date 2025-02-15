package org.para_bank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.para_bank.base.BasePage;

import java.time.Duration;

public class OpenAccountPage extends BasePage {

    private By openNewAccountButton = By.xpath("//*[@id='leftPanel']/ul/li[1]/a");
    private By accountTypeDropdown = By.xpath("//*[@id='type']");
    private By savingsOption = By.xpath("//*[@id='type']/option[2]");
    private By openAccountButton = By.xpath("//*[@id='openAccountForm']/form/div/input");
    private By successMessageLocator = By.cssSelector("#openAccountResult > p:nth-of-type(1)");
    private By fromAccountIdDropdown = By.xpath("//*[@id='fromAccountId']");

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public void openNewAccount() {
        click(openNewAccountButton);
    }

    public void selectSavingsAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar a que el dropdown de tipo de cuenta sea visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountTypeDropdown));
        click(accountTypeDropdown);

        // Esperar a que la opción de ahorros sea visible y hacer clic en ella
        wait.until(ExpectedConditions.elementToBeClickable(savingsOption));
        click(savingsOption);

        // Seleccionar la primera opción del dropdown fromAccountId
        Select fromAccountDropdown = new Select(driver.findElement(fromAccountIdDropdown));
        fromAccountDropdown.selectByIndex(0);
    }

    public void submitOpenAccount() {
        // Hacer clic en el botón de abrir cuenta
        click(openAccountButton);

        // Esperar a que aparezca el mensaje de éxito después de hacer clic en abrir cuenta
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));
    }

    public String getSuccessMessage() {
        // Esperar a que aparezca el mensaje de éxito antes de obtenerlo
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));

        WebElement successMessageElement = driver.findElement(successMessageLocator);
        return successMessageElement.getText();
    }
}