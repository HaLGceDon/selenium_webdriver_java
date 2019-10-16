package utils.pages.pages;

import org.openqa.selenium.WebDriver;
import utils.pages.pageElements.MainPageElements;


public class MainPage extends MainPageElements {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void fillLoginField(String login) {
        getLoginField().sendKeys(login);
    }

    public void selectDomain(String domain) {
        getDomain().click();
        getOption(domain).click();
    }

    public void clickSubmitButton() {
        getSubmitButton().click();
    }

    public void fillPasswordField(String password) {
        getPasswordField().sendKeys(password);
    }
}
