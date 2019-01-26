package com.grigoriev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OzonTest {
    private static WebDriver driver;
    private static String productName;
    private static int productPrice;


    @Test
    public static void test01() {
        System.setProperty("webdriver.gecko.driver", "F:\\Java\\GeckoDriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.get("https://ozon.ru/");
    }

    @Test
    public static void test02() {
        WebElement catalogButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/header/div[3]/div/div/div[1]/span"));
        catalogButton.click();
        WebElement musicButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/header/div[3]/div/div/div[1]/div/div[1]/div/a[16]/span"));
        musicButton.click();
    }

    @Test
    public static void test03() {
        WebElement tittle = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[3]/div/div[2]/div[1]/h1"));
        String sTittle = tittle.getText();
        Assert.assertEquals("Музыка", sTittle);
    }

    @Test
    public static void test04() {
        WebElement firstProduct = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[3]/div/div[2]/div[2]/div/div[1]/div/div[1]/a/div[3]/span"));
        firstProduct.click();
    }

    @Test
    public static void test05() {
        if (driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]")).isDisplayed()) {
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]")).click();
        }
        driver.navigate().refresh();
        WebElement name = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/h1"));
        productName = name.getText();
        WebElement price = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[2]/span[1]"));
        String productPriceString = price.getText().replaceAll("[^0-9]+", "");
        productPrice = Integer.parseInt(productPriceString);
    }

    @Test
    public static void test06() {
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[5]/div")).click();
    }

    @Test
    public static void test07() {
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[5]/div/a")).click();
    }

    @Test
    public static void test08() {
        WebElement productNameInBasket = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div/a"));
        String productNameInBasketString= productNameInBasket.getText();
        WebElement productPriceInBasket = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div[2]/div/div[1]/div/div/div[4]/div[2]/span/span[1]/span"));
        String productPriceInBasketString = productPriceInBasket.getText().replaceAll("[^0-9]+", "");
        int productPriceInBasketInt = Integer.parseInt(productPriceInBasketString);
        Assert.assertEquals(productName, productNameInBasketString);
        Assert.assertEquals(productPrice, productPriceInBasketInt);
    }

    @Test
    public static void test09() {
        WebElement productCheckBox = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/div[2]/div[2]/div[1]/label"));
        if (productCheckBox.isSelected()) {
            productCheckBox.click();
        }
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[3]/div[2]/span/button")).click();

    }

    @Test
    public static void test10() {
        WebElement emptyBasket = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div/h1"));
        Assert.assertEquals("Корзина пуста", emptyBasket.getText());
    }


   @Test
    public static void test11() {
        driver.quit();
    }

}
