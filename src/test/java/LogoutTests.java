import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import model.AccountPageElements;
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
import static config.Config.randomPass;

public class LogoutTests {

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
        // регистрация
        objMainPage.clickAccountButton();
        objFormPage.clickRegisterLink();
        objFormPage.fillRegisterForm(randomName, randomMail, randomPass);
        objFormPage.clickRegisterButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//h2[contains(text(), 'Вход')]")));
        // авторизация
        objFormPage.fillLoginForm(randomMail, randomPass);
        objFormPage.clickLoginButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")));
    }

    @Test
    @DisplayName("Выход из системы")
    public void logout() {
        // переходим на страницу приложения
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPageElements objMainPage = new MainPageElements(driver);
        AccountPageElements objAccountPage = new AccountPageElements(driver);

        objMainPage.clickAccountButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//p[contains(text(), 'В этом разделе вы можете изменить свои персональные данные')]")));
        String URL = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/account/profile", URL);

        objAccountPage.clickLogoutButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//h2[contains(text(), 'Вход')]")));
        String URL2 = driver.getCurrentUrl();
        driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/login", URL2);
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }

}
