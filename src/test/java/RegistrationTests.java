import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import model.MainPageElements;
import model.FormPageElements;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static config.Config.*;
import static org.junit.Assert.assertTrue;

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

    // Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
    @Test
    @DisplayName("Регистрация с валидными данными")
    public void registerValidData() {
        // переходим на страницу приложения
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
    @DisplayName("Проверка ошибки при вводе пароля менее 5 символов")
    public void registerInvalidPassword() {
        // переходим на страницу приложения
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);

        objMainPage.clickAccountButton();
        objFormPage.clickRegisterLink();
        objFormPage.fillRegisterForm(randomName, randomMail, "12345");
        objFormPage.clickRegisterButton();

        WebElement actualText = driver.findElement(By.xpath("//p[contains(text(), 'Некорректный пароль')]"));
        boolean textPresent = actualText.isDisplayed();
        assertTrue(textPresent);
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
