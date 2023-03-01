import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import model.MainPageElements;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static config.Config.mainUrl;

public class ConstructorTests {
    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Проверка вкладок у бургеров")
    public void checkTabs() {
        // для проверки данного кейса регистрация не требуется
        // переходим на страницу приложения
        driver.get(mainUrl);

        MainPageElements objMainPage = new MainPageElements(driver);

        objMainPage.clickTabFillingButton();
        objMainPage.findBunText();

        objMainPage.clickTabSauceButton();
        objMainPage.findSauceTExt();

        objMainPage.clickTabBunButton();
        objMainPage.findFillingText();
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
