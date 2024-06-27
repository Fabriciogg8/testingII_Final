package org.para_bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.para_bank.base.BasePage;

public class AccountActivityPage extends BasePage {

    private By accountsOverviewLink = By.xpath("//*[@id='leftPanel']/ul/li[2]/a");
    private By balanceIncludesTextLocator = By.xpath("//*[@id='accountTable']/tfoot/tr/td");
    private By firstAccountLinkLocator = By.xpath("//*[@id='accountTable']/tbody/tr[1]/td[1]/a");
    private By accountDetailsTitleLocator = By.xpath("//*[@id='accountDetails']/h1");
    private By monthDropdown = By.id("month");
    private By transactionTypeDropdown = By.id("transactionType");
    private By goButton = By.xpath("//input[@value='Go']");

    public AccountActivityPage(WebDriver driver) {
        super(driver);
    }

    public void goToAccountsOverview() {
        click(accountsOverviewLink);
    }

    public boolean isBalanceIncludesTextPresent() {
        return isElementPresent(balanceIncludesTextLocator);
    }

    public void clickFirstAccount() {
        click(firstAccountLinkLocator);
    }

    public boolean isAccountDetailsTitlePresent() {
        return isElementPresent(accountDetailsTitleLocator);
    }

    public void selectMonth(String month) {
        Select monthSelect = new Select(driver.findElement(monthDropdown));
        monthSelect.selectByVisibleText(month);
    }

    public void selectTransactionType(String type) {
        Select transactionTypeSelect = new Select(driver.findElement(transactionTypeDropdown));
        transactionTypeSelect.selectByVisibleText(type);
    }

    public void clickGo() {
        click(goButton);
    }

    // Métodos públicos para obtener los localizadores de los elementos privados
    public By getBalanceIncludesTextLocator() {
        return balanceIncludesTextLocator;
    }

    public By getFirstAccountLinkLocator() {
        return firstAccountLinkLocator;
    }

    public By getAccountDetailsTitleLocator() {
        return accountDetailsTitleLocator;
    }
}