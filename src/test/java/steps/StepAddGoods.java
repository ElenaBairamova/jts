package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class StepAddGoods {
        private static WebDriver driver;

        @Дано("открыт стенд")
        public void openStand() {
            driver = new ChromeDriver();
            driver.manage ().timeouts ().implicitlyWait (10, TimeUnit.SECONDS);
            driver.manage ().window ().maximize ();
            driver.get ("http://149.154.71.152:8080/");

        }

        @Когда("Пользователь нажимает на выпадающий список {string}")
        public void clickSandboxButton(String string) {
            WebElement sandboxButton = driver.findElement(By.xpath("//a [@id='navbarDropdown']"));
            sandboxButton.click();
        }

        @И("Пользователь выбирает в выпадающем списке пункт {string}")
        public void clickGoodsButton(String string) {
            WebElement goodsButton = driver.findElement(By.xpath("//a [@href='/food']"));
            goodsButton.click();
        }

        @И("Пользователь нажимает на кнопку {string}")
        public void clickAddButton(String string) {
            WebElement addButton = driver.findElement(By.xpath("//button[text()='Добавить']"));
            addButton.click();
        }

        @И("Пользователь вводит наименование {string}")
        public void NameGoodExoticFruit(String name) {
            WebElement nameGood = driver.findElement(By.xpath("//input[@id='name']"));
            nameGood.sendKeys(name);
        }

        @И("Пользователь нажимает на тип и выбирает в выпадающем списке тип {string}")
        public void clickDropdownTypeFruit(String goodType) {
            WebElement goodType1 = driver.findElement(By.id("type"));
            goodType1.click();
            if (Objects.equals(goodType, "Овощ")) {
                WebElement selectedFoodType = driver.findElement(By.xpath("//option[@value='VEGETABLE']"));
                selectedFoodType.click();
            } else if (Objects.equals(goodType, "Фрукт")) {
                WebElement selectedFoodType = driver.findElement(By.xpath("//option[@value='FRUIT']"));
                selectedFoodType.click();
            }
        }

        @И("Пользователь выбирает чек-бокс Экзотический")
        public void clickCheckboxExotic() {
            WebElement checkBoxExotic = driver.findElement(By.id("exotic"));
            checkBoxExotic.click();
        }

        @И("Пользователь нажимает кнопку {string}")
        public void clickSaveButton(String string) {
            WebElement saveButton = driver.findElement(By.id("save"));
            saveButton.click();

        }

        @Тогда("Проверяем, что в последней строке таблицы Товары отображаются введенные данные экзотического фрукта")
        public void assertResultExoticFruit() {


            List<String> listExoticFruit = Arrays.asList(
                    "5", "Папайя", "Фрукт", "true");

            List<WebElement> lastRowInTableOfGoods = driver.findElements(By.xpath(
                    "//tr[5]/*"));

            List<String> lastRowInTableOfGoodsStr = new ArrayList<>();

            for (WebElement element : lastRowInTableOfGoods) {
                lastRowInTableOfGoodsStr.add(element.getText());
            }

            Assertions.assertEquals(
                    listExoticFruit,
                    lastRowInTableOfGoodsStr,
                    "Не совпадают элементы в таблице товаров");
        }

        @Тогда("Проверяем, что в последней строке таблицы Товары отображаются введенные данные фрукта")
        public void assertResultNotExoticFruit() {


            List<String> listExoticFruit = Arrays.asList(
                    "5", "Банан", "Фрукт", "false");


            List<WebElement> lastRowInTableOfGoods = driver.findElements(By.xpath(
                    "//tr[5]/*"));

            List<String> lastRowInTableOfGoodsStr = new ArrayList<>();

            for (WebElement element : lastRowInTableOfGoods) {
                lastRowInTableOfGoodsStr.add(element.getText());
            }

            Assertions.assertEquals(
                    listExoticFruit,
                    lastRowInTableOfGoodsStr,
                    "Не совпадают элементы в таблице товаров");
        }

        @Тогда("Проверяем, что в последней строке таблицы Товары отображаются введенные данные экзотического овоща")
        public void assertResultExoticVegetable() {


            List<String> listExoticFruit = Arrays.asList(
                    "5", "Кивано", "Овощ", "true");


            List<WebElement> lastRowInTableOfGoods = driver.findElements(By.xpath(
                    "//tr[5]/*"));

            List<String> lastRowInTableOfGoodsStr = new ArrayList<>();

            for (WebElement element : lastRowInTableOfGoods) {
                lastRowInTableOfGoodsStr.add(element.getText());
            }

            Assertions.assertEquals(
                    listExoticFruit,
                    lastRowInTableOfGoodsStr,
                    "Не совпадают элементы в таблице товаров");
        }

        @Тогда("Проверяем, что в последней строке таблицы Товары отображаются введенные данные овоща")
        public void assertResultFruit() {


            List<String> listExoticFruit = Arrays.asList(
                    "5", "Огурец", "Овощ", "false");


            List<WebElement> lastRowInTableOfGoods = driver.findElements(By.xpath(
                    "//tr[5]/*"));

            List<String> lastRowInTableOfGoodsStr = new ArrayList<>();

            for (WebElement element : lastRowInTableOfGoods) {
                lastRowInTableOfGoodsStr.add(element.getText());
            }

            Assertions.assertEquals(
                    listExoticFruit,
                    lastRowInTableOfGoodsStr,
                    "Не совпадают элементы в таблице товаров");
        }

        @И("Пользователь выбирает в выпадающем списке пункт Сброс данных")
        public void clickDeleteData() {
            WebElement deleteDataButton = driver.findElement(By.xpath("//a [@id='reset']"));
            deleteDataButton.click();
        }

        @Тогда("Закрываем соединение")
        public void finish() {
            driver.close();
            driver.quit();
        }

    }
