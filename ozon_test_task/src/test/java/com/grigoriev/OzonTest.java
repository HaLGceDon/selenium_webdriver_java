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


    @Test (description = "Настроить driver и открыть в браузере сайт https://www.ozon.ru/")
    public static void test01() {
        System.setProperty("webdriver.gecko.driver", "F:\\Java\\GeckoDriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.get("https://ozon.ru/");
    }

    @Test (description = "В меню \"Каталог товаров\" выбрать категорию \"Музыка\".")
    public static void test02() {
        WebElement catalogButton = driver.findElement(By.xpath("//*[@data-test-id=\"menu-show-dropdown\"]"));
        catalogButton.click();
        WebElement musicButton = driver.findElement(By.xpath("//span[@class=\"section-title\"][contains(.,\"Музыка\")]"));
        musicButton.click();
    }

    @Test (description = "Проверить, что открылся список товаров.")
    public static void test03() {
        WebElement title = driver.findElement(By.xpath("//h1[@class=\"title\"]"));
        String sTittle = title.getText();
        Assert.assertEquals("Музыка", sTittle);
    }

    @Test (description = "Выбрать первый товар.")
    public static void test04() {
        WebElement firstProduct = driver.findElement(By.xpath("(//span[@class=\"item-title\"])[1]"));
        firstProduct.click();
    }

    @Test (description = "Запомнить стоимость и название данного товара.")
    public static void test05() {
        if (driver.findElement(By.xpath("//div[@class=\"close-icon eDYbanner_close jsCloseBanner\"]")).isDisplayed()) {
            driver.findElement(By.xpath("//div[@class=\"close-icon eDYbanner_close jsCloseBanner\"]")).click();
        }
        driver.navigate().refresh();
        WebElement name = driver.findElement(By.xpath("//h1[@class=\"bItemName\"]"));
        productName = name.getText();
        WebElement price = driver.findElement(By.xpath("//span[@class=\"eOzonPrice_main\"]"));
        String productPriceString = price.getText().replaceAll("[^0-9]+", "");
        productPrice = Integer.parseInt(productPriceString);
    }

    @Test (description = "Добавить его в корзину.")
    public static void test06() {
        driver.findElement(By.xpath("//div[@class=\"bSaleBlockButton jsButton\"]")).click();
    }

    @Test (description = "Открыть корзину.")
    public static void test07() {
        driver.findElement(By.xpath("//a[@class=\"bSaleBlockButton mTwoLines mActive\"]")).click();
    }

    @Test (description = "Проверить то, что в корзине находится раннее выбранный товар и его стоимость равна сохраненной стоимости.")
    public static void test08() {
        WebElement productNameInBasket = driver.findElement(By.xpath("//a[@class=\"title\"]"));
        String productNameInBasketString= productNameInBasket.getText();
        WebElement productPriceInBasket = driver.findElement(By.xpath("(//span[@class=\"main\"])[1]"));
        String productPriceInBasketString = productPriceInBasket.getText().replaceAll("[^0-9]+", "");
        int productPriceInBasketInt = Integer.parseInt(productPriceInBasketString);
        Assert.assertEquals(productName, productNameInBasketString);
        Assert.assertEquals(productPrice, productPriceInBasketInt);
    }

    @Test (description = "Выбрать этот товар в корзине и «Удалить» из корзины.")
    public static void test09() {
        WebElement productCheckBox = driver.findElement(By.xpath("//span[@class=\"checkmark\"]"));
        if (productCheckBox.isSelected()) {
            productCheckBox.click();
        }
        driver.findElement(By.xpath("//button[@class=\"box-btn button default\"]")).click();

    }

    @Test (description = "Проверить, что корзина пуста.")
    public static void test10() {
        WebElement emptyBasket = driver.findElement(By.xpath("//h1[@class=\"cart-title\"]"));
        Assert.assertEquals("Корзина пуста", emptyBasket.getText());
    }


   @Test (description = "Закрыть браузер.")
    public static void test11() {
        driver.quit();
    }

}
