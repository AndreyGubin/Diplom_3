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

public class NavigationTests {

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
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//h2[contains(text(), 'Вход')]")));
        objFormPage.fillLoginForm(randomMail, randomPass);
        objFormPage.clickLoginButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath(".//button[@class = 'button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']")));
    }

    @Test
    @DisplayName("Переход в конструктор из аккаунта")
    public void constructor() {
//         переходим на страницу приложения
        driver.get("https://stellarburgers.nomoreparties.site/");

        MainPageElements objMainPage = new MainPageElements(driver);
        AccountPageElements objAccountPage = new AccountPageElements(driver);

        objMainPage.clickAccountButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//p[contains(text(), 'В этом разделе вы можете изменить свои персональные данные')]")));
        String URL = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/account/profile", URL);
        objAccountPage.clickConstructorButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//p[contains(text(), 'Флюоресцентная булка R2-D3')]")));
        String URL2 = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", URL2);
    }

    @Test
    @DisplayName("Переход в конструктор кликом по лого из аккаунта")
    public void logo() {
        // переходим на страницу приложения
        driver.get("https://stellarburgers.nomoreparties.site/");

        MainPageElements objMainPage = new MainPageElements(driver);
        AccountPageElements objAccountPage = new AccountPageElements(driver);

        objMainPage.clickAccountButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//p[contains(text(), 'В этом разделе вы можете изменить свои персональные данные')]")));
        String URL = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/account/profile", URL);
        objAccountPage.clickLogoButtonButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//p[contains(text(), 'Флюоресцентная булка R2-D3')]")));
        String URL2 = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/", URL2);
    }

    @Test
    @DisplayName("Переход в аккаунт")
    public void goToAccount() {
        // переходим на страницу приложения
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPageElements objMainPage = new MainPageElements(driver);
        objMainPage.clickAccountButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//p[contains(text(), 'В этом разделе вы можете изменить свои персональные данные')]")));
        String URL = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/account/profile", URL);
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
