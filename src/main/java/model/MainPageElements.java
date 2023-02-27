package model;

import org.openqa.selenium.*;

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
    // локаторы табов
    private final By tabBun = By.xpath(".//div/span[text()='Булки']");
    private final By tabSauce = By.xpath(".//div/span[text()='Соусы']");
    private final By tabDrink = By.xpath(".//div/span[text()='Начинки']");

    // клики по кнопкам входа
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
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
}
