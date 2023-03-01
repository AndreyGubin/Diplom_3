package model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageElements {

    private final WebDriver driver;

    public MainPageElements(WebDriver driver) {
        this.driver = driver;
    }

    // локатор кнопки личный кабинет в верхнем правом углу
    private final By accountButton = By.xpath(".//p[@class = 'AppHeader_header__linkText__3q_va ml-2' and (text() = 'Личный Кабинет')]");
    // локатор кнопки войти в аккаунт в правой части страницы
    private final By loginButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' " +
            "and (text() = 'Войти в аккаунт')]");
    private final By orderButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' " +
            "and (text() = 'Оформить заказ')]");
    // локаторы табов
    private final By tabBun = By.xpath(".//div/span[text()='Булки']");
    private final By tabSauce = By.xpath(".//div/span[text()='Соусы']");
    private final By tabDrink = By.xpath(".//div/span[text()='Начинки']");
    private final By bunText = By.xpath("//p[contains(text(), 'Флюоресцентная булка R2-D3')]");
    private final By sauceText = By.xpath("//p[contains(text(), 'Соус Spicy-X')]");
    private final By fillingText = By.xpath("//p[contains(text(), 'Мясо бессмертных моллюсков Protostomia')]");

    // клики по кнопкам входа
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void findOrderButton() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(orderButton));
        driver.findElement(orderButton);
    }

    // клики по табам
    public void clickTabBunButton() {
        driver.findElement(tabBun).click();
    }

    public void clickTabSauceButton() {
        driver.findElement(tabSauce).click();
    }

    public void clickTabFillingButton() {
        driver.findElement(tabDrink).click();
    }

    public void findBunText() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(bunText));
    }

    public void findSauceTExt() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(sauceText));
    }

    public void findFillingText() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(fillingText));
    }
}
