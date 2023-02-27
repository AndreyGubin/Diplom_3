import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import model.FormPageElements;
import model.MainPageElements;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static config.Config.*;

public class LoginTests {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        randomMail = RandomStringUtils.randomAlphabetic(10).toLowerCase() + "@yandex.ru";
        randomPass = RandomStringUtils.randomAlphabetic(10);
        randomName = RandomStringUtils.randomAlphabetic(10).toLowerCase();

        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);

        objMainPage.clickAccountButton();
        objFormPage.clickRegisterLink();
        objFormPage.fillRegisterForm(randomName, randomMail, randomPass);
        objFormPage.clickRegisterButton();

        // в случае успешной регистрации отобразится страница входа, ожидаем текст и проверяем URL
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//h2[contains(text(), 'Вход')]")));
        String URL = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/login", URL);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginValidData() {
        // переходим на страницу приложения
        driver.get("https://stellarburgers.nomoreparties.site/");

        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);

        objMainPage.clickAccountButton();
        objFormPage.fillLoginForm(randomMail, randomPass);
        objFormPage.clickLoginButton();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginValidData2() {
        // переходим на страницу приложения
        driver.get("https://stellarburgers.nomoreparties.site/");

        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);

        objMainPage.clickLoginButton();
        objFormPage.fillLoginForm(randomMail, randomPass);
        objFormPage.clickLoginButton();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginValidData3() {
        // переходим на страницу приложения
        driver.get("https://stellarburgers.nomoreparties.site/");

        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);

        objMainPage.clickAccountButton();

        objFormPage.clickRegisterLink();
        objFormPage.clickLoginRegisterButton();

        objFormPage.fillLoginForm(randomMail, randomPass);
        objFormPage.clickLoginButton();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginValidData4() {
        // переходим на страницу приложения
        driver.get("https://stellarburgers.nomoreparties.site/");

        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);

        objMainPage.clickAccountButton();
        objFormPage.clickResetPassLink();
        objFormPage.clickLoginResetPassLink();
        objFormPage.fillLoginForm(randomMail, randomPass);
        objFormPage.clickLoginButton();
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }


}
