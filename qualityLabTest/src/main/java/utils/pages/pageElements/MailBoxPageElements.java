package utils.pages.pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;


public class MailBoxPageElements {

    public MailBoxPageElements(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;


    private WebElement getComposeWindow() {
        List<WebElement> containers = driver.findElements(By.xpath("//div[contains(@class, 'compose-app_window')]"));
        Assert.assertEquals(containers.size(), 1);
        return containers.get(0);
    }

    protected WebElement getWriteMailButton() {
        List<WebElement> elements = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='compose-button__txt']")));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected WebElement getToWhomField() {
        List<WebElement> elements = getComposeWindow().findElements(By.xpath(".//div[contains(@class, 'contactsContainer')]//input"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected WebElement getSubjectField() {
        List<WebElement> elements = getComposeWindow().findElements(By.xpath(".//input[@name='Subject']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected WebElement getTextBox() {
        List<WebElement> elements = getComposeWindow().findElements(By.xpath(".//div[@role='textbox']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected WebElement getSendButton() {
        List<WebElement> elements = getComposeWindow().findElements(By.xpath(".//span[@title='Отправить']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }
}
