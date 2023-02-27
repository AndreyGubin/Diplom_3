package model;

import org.openqa.selenium.*;

public class FormPageElements {

    private final WebDriver driver;

    public FormPageElements(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameField = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    private final By emailField = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    private final By passField = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    private final By registerButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and (text() = 'Зарегистрироваться')]");
    private final By registerLink = By.xpath(".//a[@class = 'Auth_link__1fOlj']");
    private final By resetPassLink = By.xpath(".//a[@class = 'Auth_link__1fOlj']");
    private final By loginButton = By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' " +
            "and (text() = 'Войти')]");
    private final By loginRegisterButton = By.xpath(".//a[@class = 'Auth_link__1fOlj']");
    private final By loginResetPassLink = By.xpath(".//a[@class = 'Auth_link__1fOlj']");

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPass(String pass) {
        driver.findElement(passField).sendKeys(pass);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    public void clickResetPassLink() {
        driver.findElement(resetPassLink).click();
    }

    public void clickLoginResetPassLink() {
        driver.findElement(loginResetPassLink).click();
    }

    public void fillRegisterForm(String name, String email, String pass) {
        setName(name);
        setEmail(email);
        setPass(pass);
    }

    public void fillLoginForm(String email, String pass) {
        setEmail(email);
        setPass(pass);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickLoginRegisterButton() {
        driver.findElement(loginRegisterButton).click();
    }
}
