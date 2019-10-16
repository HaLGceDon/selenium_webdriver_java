package utils.pages.pages;

import org.openqa.selenium.WebDriver;
import utils.pages.pageElements.MailBoxPageElements;


public class MailBoxPage extends MailBoxPageElements {

    public MailBoxPage(WebDriver driver) {
        super(driver);
    }

    public void clickWriteMailButton() {
        getWriteMailButton().click();
    }

    public void fillToWhomField(String text) {
        getToWhomField().sendKeys(text);
    }

    public void fillSubjectField(String text) {
        getSubjectField().sendKeys(text);
    }

    public void fillTextBox(String text) {
        getTextBox().sendKeys(text);
    }

    public void clickSendMailButton() {
        getSendButton().click();
    }
}
