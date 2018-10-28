import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

    public class FirstTest {
        private WebDriver driver;
        private String baseUrl;

        @Before
        public void setUp(){
            driver = new ChromeDriver();
            baseUrl = "https://moscow-job.tinkoff.ru/";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testFirst(){
            driver.get("https://moscow-job.tinkoff.ru/");
            driver.findElement(By.name("fio")).click();
            driver.findElement(By.name("email")).click();
            driver.findElement(By.name("phone")).click();
            driver.findElement(By.name("city")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Поле обязательное'])[3]/following::div[8]")).click();
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Пройдите обучение'])[1]/following::article[1]")).click();
            assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия, имя и отчество'])[1]/following::div[3]")).getText());
            assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[3]")).getText());
            assertEquals("Необходимо указать номер телефона", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номер телефона'])[1]/following::div[3]")).getText());
            assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[3]")).getText());
            assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Продавец-консультант'])[2]/following::div[1]")).getText());
        }

        @Test
        public void testSecond() {
            driver.get("https://moscow-job.tinkoff.ru/");
            driver.findElement(By.name("fio")).click();
            driver.findElement(By.name("fio")).clear();
            driver.findElement(By.name("fio")).sendKeys("Zxc");
            driver.findElement(By.name("email")).click();
            driver.findElement(By.name("email")).clear();
            driver.findElement(By.name("email")).sendKeys("qwe");
            driver.findElement(By.name("phone")).click();
            driver.findElement(By.name("phone")).clear();
            driver.findElement(By.name("phone")).sendKeys("+7 (111)");
            driver.findElement(By.name("city")).click();
            driver.findElement(By.name("city")).clear();
            driver.findElement(By.name("city")).sendKeys("city1");
            driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Допустимо использовать только буквы русского, латинского алфавита и&nbsp;дефис'])[1]/following::div[6]")).click();
            assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия, имя и отчество'])[1]/following::div[3]")).getText());
            assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[3]")).getText());
            assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номер телефона'])[1]/following::div[3]")).getText());
            assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[3]")).getText());
            assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Продавец-консультант'])[2]/following::div[1]")).getText());
        }

        @After
        public void tearDown() {
            driver.quit();
        }
    }

