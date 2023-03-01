import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import model.MainPageElements;
import model.FormPageElements;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static config.Config.*;

public class RegistrationTests {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        randomMail = RandomStringUtils.randomAlphabetic(10).toLowerCase() + "@yandex.ru";
        randomPass = RandomStringUtils.randomAlphabetic(10);
        randomName = RandomStringUtils.randomAlphabetic(10).toLowerCase();
    }

    @Test
    @DisplayName("Регистрация с валидными данными")
    public void registerValidData() {
        // переходим на страницу приложения
        driver.get(mainUrl);
        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);

        objMainPage.clickAccountButton();
        objFormPage.clickRegisterLink();
        objFormPage.fillRegisterForm(randomName, randomMail, randomPass);
        objFormPage.clickRegisterButton();

        // в случае успешной регистрации отобразится страница входа, ожидаем текст и проверяем URL
        objFormPage.findLoginText();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(loginUrl, URL);
    }

    @Test
    @DisplayName("Проверка ошибки при вводе пароля менее 5 символов")
    public void registerInvalidPassword() {
        // переходим на страницу приложения
        driver.get(mainUrl);
        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);

        objMainPage.clickAccountButton();
        objFormPage.clickRegisterLink();
        objFormPage.fillRegisterForm(randomName, randomMail, "12345");
        objFormPage.clickRegisterButton();
        objFormPage.findIncorrectPassText();
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
