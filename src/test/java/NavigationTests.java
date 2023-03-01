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

        driver.get(mainUrl);
        MainPageElements objMainPage = new MainPageElements(driver);
        FormPageElements objFormPage = new FormPageElements(driver);

        objMainPage.clickAccountButton();
        objFormPage.clickRegisterLink();
        objFormPage.fillRegisterForm(randomName, randomMail, randomPass);
        objFormPage.clickRegisterButton();
        objFormPage.findLoginText();
        objFormPage.fillLoginForm(randomMail, randomPass);
        objFormPage.clickLoginButton();
        objMainPage.findOrderButton();
    }

    @Test
    @DisplayName("Переход в конструктор из аккаунта")
    public void constructor() {
        // переходим на страницу приложения
        driver.get(mainUrl);

        MainPageElements objMainPage = new MainPageElements(driver);
        AccountPageElements objAccountPage = new AccountPageElements(driver);

        objMainPage.clickAccountButton();
        objAccountPage.findPersonalDataText();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(profileUrl, URL);
        objAccountPage.clickConstructorButton();
        objMainPage.findBunText();
        String URL2 = driver.getCurrentUrl();
        Assert.assertEquals(mainUrl, URL2);
    }

    @Test
    @DisplayName("Переход в конструктор кликом по лого из аккаунта")
    public void logo() {
        // переходим на страницу приложения
        driver.get(mainUrl);

        MainPageElements objMainPage = new MainPageElements(driver);
        AccountPageElements objAccountPage = new AccountPageElements(driver);

        objMainPage.clickAccountButton();
        objAccountPage.findPersonalDataText();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(profileUrl, URL);
        objAccountPage.clickLogoButtonButton();
        objMainPage.findBunText();
        String URL2 = driver.getCurrentUrl();
        Assert.assertEquals(mainUrl, URL2);
    }

    @Test
    @DisplayName("Переход в аккаунт")
    public void goToAccount() {
        // переходим на страницу приложения
        driver.get(mainUrl);
        MainPageElements objMainPage = new MainPageElements(driver);
        AccountPageElements objAccountPage = new AccountPageElements(driver);
        objMainPage.clickAccountButton();
        objAccountPage.findPersonalDataText();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(profileUrl, URL);
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
