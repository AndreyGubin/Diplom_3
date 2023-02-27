import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import model.MainPageElements;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        driver.get("https://stellarburgers.nomoreparties.site/");

        MainPageElements objMainPage = new MainPageElements(driver);

        objMainPage.clickTabFillingButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//p[contains(text(), 'Мясо бессмертных моллюсков Protostomia')]")));

        objMainPage.clickTabSauceButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//p[contains(text(), 'Соус Spicy-X')]")));

        objMainPage.clickTabBunButton();
        new WebDriverWait(driver, 5).until(ExpectedConditions.
                presenceOfElementLocated(By.xpath("//p[contains(text(), 'Флюоресцентная булка R2-D3')]")));
    }

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
