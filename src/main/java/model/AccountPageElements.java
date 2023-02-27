package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPageElements {

    private final WebDriver driver;

    public AccountPageElements(WebDriver driver) {
        this.driver = driver;
    }

    private final By constructorButton = By.xpath(".//p[@class = 'AppHeader_header__linkText__3q_va ml-2' and (text() = 'Конструктор')]");
    private final By logoButton = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    private final By logoutButton = By.xpath(".//button[@class = 'Account_button__14Yp3 text text_type_main-medium text_color_inactive' and (text() = 'Выход')]");

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogoButtonButton() {
        driver.findElement(logoButton).click();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}
