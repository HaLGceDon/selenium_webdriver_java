package utils.pages.pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class MainPageElements {

    public MainPageElements(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;


    private WebElement getMailboxContainer() {
        List<WebElement> containers = driver.findElements(By.xpath("//div[@id='mailbox-container']"));
        Assert.assertEquals(containers.size(), 1);
        return containers.get(0);
    }

    protected WebElement getLoginField() {
        List<WebElement> elements = getMailboxContainer().findElements(By.xpath(".//input[@id='mailbox:login']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected WebElement getDomain() {
        List<WebElement> elements = getMailboxContainer().findElements(By.xpath(".//select[@id='mailbox:domain']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected WebElement getOption(String option) {
        List<WebElement> elements = getDomain().findElements(By.xpath(String.format("./option[text()='%s']", option)));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected WebElement getSubmitButton() {
        List<WebElement> elements = getMailboxContainer().findElements(By.xpath(".//label[@id='mailbox:submit']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }

    protected WebElement getPasswordField() {
        List<WebElement> elements = getMailboxContainer().findElements(By.xpath(".//input[@id='mailbox:password']"));
        Assert.assertEquals(elements.size(), 1);
        return elements.get(0);
    }
}
