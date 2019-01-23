package com.grigoriev;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "F:\\Java\\ChromeWebDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://mail.ru/");
    }
    @Test
    public void userLogin() {
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"mailbox:login\"]"));
        loginField.sendKeys("test.grigoriev");
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"mailbox:password\"]"));
        passwordField.sendKeys("zaq1234.");
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"mailbox:submit\"]/input"));
        loginButton.click();
        WebElement menuUser = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"PH_authMenu_button\"]")));
        menuUser.click();
        WebElement profileUser = driver.findElement(By.cssSelector("#PH\\3a authMenu\\3a activeAccount > div > div.w-x-ph__auth_list__item__info > div > div.x-ph__auth_list__item__info__text"));
        String mailUser = profileUser.getAttribute("innerText");
        System.out.println(mailUser);
        Assert.assertEquals("test.grigoriev@mail.ru", mailUser);
    }
    @AfterClass
    public static void tearDown() {
        WebElement logoutButton = driver.findElement(By.id("PH_logoutLink"));
        logoutButton.click();
        driver.quit();
    }
}