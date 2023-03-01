import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import model.AccountPageElements;
import model.FormPageElements;
import model.MainPageElements;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

        driver.get(mainUrl);
        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);

        // регистрация
        objMainPage.clickAccountButton();
        objFormPage.clickRegisterLink();
        objFormPage.fillRegisterForm(randomName, randomMail, randomPass);
        objFormPage.clickRegisterButton();
        objFormPage.findLoginText();
        // авторизация
        objFormPage.fillLoginForm(randomMail, randomPass);
        objFormPage.clickLoginButton();
        objMainPage.findOrderButton();
    }

    @Test
    @DisplayName("Выход из системы")
    public void logout() {
        // переходим на страницу приложения
        driver.get(mainUrl);
        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);
        AccountPageElements objAccountPage = new AccountPageElements(driver);

        objMainPage.clickAccountButton();
        objAccountPage.findPersonalDataText();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(profileUrl, URL);

        objAccountPage.clickLogoutButton();
        objFormPage.findLoginText();
        String URL2 = driver.getCurrentUrl();
        driver.getCurrentUrl();
        Assert.assertEquals(loginUrl, URL2);
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
