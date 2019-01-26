package com.grigoriev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


/**
 * Тестовое задание.
 * @author  Григорьев Василий
 * @version 1.0
 */
public class OzonTest {
    private static WebDriver driver;
    private static String productName;
    private static int productPrice;


    /**
     * Путь к geckodriver и настройка размера окна браузера.
     *Неявное ожидание видимости объекта, загрузки страницы и скриптов - 10 секунд, с 7 секундами иногда не успевает.
     * Открытие сайта ozon.ru.
     */
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


    /**
     * Найти элемент "Каталог товаров", кликнуть.
     * Найти элемент "Музыка", кликнуть.

     */
    @Test (description = "В меню \"Каталог товаров\" выбрать категорию \"Музыка\".")
    public static void test02() {
        WebElement catalogButton = driver.findElement(By.xpath("//*[@data-test-id=\"menu-show-dropdown\"]"));
        catalogButton.click();
        WebElement musicButton = driver.findElement(By.xpath("//span[@class=\"section-title\"][contains(.,\"Музыка\")]"));
        musicButton.click();
    }


    /**
     * Находит title с категорией товаров на данной странице и сравнивает с ожидаемым.
     */
    @Test (description = "Проверить, что открылся список товаров.")
    public static void test03() {
        WebElement title = driver.findElement(By.xpath("//h1[@class=\"title\"]"));
        String sTittle = title.getText();
        Assert.assertEquals("Музыка", sTittle);
    }


    /**
     * Находит и кликает на первый товар на странице.
     */
    @Test (description = "Выбрать первый товар.")
    public static void test04() {
        WebElement firstProduct = driver.findElement(By.xpath("(//span[@class=\"item-title\"])[1]"));
        firstProduct.click();
    }


    /**
     * Если выскочил рекламный баннер, то закрывает его.
     * Перезагрузка страницы после баннера.
     * Находит наименование и цену товара и сохраняет их в статические поля.
     */
    @Test (description = "Запомнить стоимость и название данного товара.")
    public static void test05() {

        if (driver.findElement(By.xpath("//div[@class=\"close-icon eDYbanner_close jsCloseBanner\"]")).isDisplayed()) {
            driver.findElement(By.xpath("//div[@class=\"close-icon eDYbanner_close jsCloseBanner\"]")).click();
            driver.navigate().refresh();
        }
        WebElement name = driver.findElement(By.xpath("//h1[@class=\"bItemName\"]"));
        productName = name.getText();
        WebElement price = driver.findElement(By.xpath("//span[@class=\"eOzonPrice_main\"]"));
        String productPriceString = price.getText().replaceAll("[^0-9]+", "");
        productPrice = Integer.parseInt(productPriceString);
    }


    /**
     * Находит элемент "Добавить в корзину" и кликает на него.
     */
    @Test (description = "Добавить его в корзину.")
    public static void test06() {
        driver.findElement(By.xpath("//div[@class=\"bSaleBlockButton jsButton\"]")).click();
    }


    /**
     * Находит элемент "Перейти в корзину" и кликает на него.
     */
    @Test (description = "Открыть корзину.")
    public static void test07() {
        driver.findElement(By.xpath("//a[@class=\"bSaleBlockButton mTwoLines mActive\"]")).click();
    }


    /**
     * Находит название и цену товара в корзине.
     *          * Сравнивает найденные цены в корзине, с сохраненными.

     */
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


    /**
     * Если товар не выбран, то выбирает его.
     * Кликает на кнопку "Удалить все выбранные товары".
     */
    @Test (description = "Выбрать этот товар в корзине и «Удалить» из корзины.")
    public static void test09() {

        WebElement productCheckBox = driver.findElement(By.xpath("//span[@class=\"checkmark\"]"));
        if (productCheckBox.isSelected()) {
            productCheckBox.click();
        }
        driver.findElement(By.xpath("//*[@data-test-id=\"cart-delete-selected-btn\"]")).click();

    }


    /**
     * Находит элемент title и сравнивает его с ожидаемым: "Корзина пуста".
     */
    @Test (description = "Проверить, что корзина пуста.")
    public static void test10() {

        WebElement emptyBasket = driver.findElement(By.xpath("//h1[@class=\"cart-title\"]"));
        Assert.assertEquals("Корзина пуста", emptyBasket.getText());
    }


    /**
     * Закрывает браузер.
     */
   @Test (description = "Закрыть браузер.")
    public static void test11() {
       driver.quit();
    }

}
