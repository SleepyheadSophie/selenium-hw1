import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.junit.Test;




    public class FirstTest extends BaseRuner{

        @Test
        public void testFirst(){
            driver.get(baseUrl);
            driver.findElement(By.xpath("//input[@name='fio']")).click();
            driver.findElement(By.xpath("//input[@name='email']")).click();
            driver.findElement(By.xpath("//input[@name='phone']")).click();
            driver.findElement(By.xpath("//input[@name='city']")).click();
            driver.findElement(By.xpath("//form//div[@class='SelectItem__data_3u2ir SelectItem__placeholder_3_ypX SelectItem__isRequired_2J1mB SelectItem__data_noWrap_FIC1K']")).click();
            driver.findElement(By.xpath("//input[@name='city']")).click();
            assertEquals("Поле обязательное", driver
                    .findElement(By.xpath("//form//div[.//@name='fio']/following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
            assertEquals("Поле обязательное", driver
                    .findElement(By.xpath("//form//div[.//@name='email']/following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
            assertEquals("Необходимо указать номер телефона", driver
                    .findElement(By.xpath("//form//div[.//@name='phone']/following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
            assertEquals("Поле обязательное", driver
                    .findElement(By.xpath("//form//div[.//@name='city']/following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
            assertEquals("Поле обязательное", driver
                    .findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Продавец-консультант'])[2]/following::div[1]")).getText());
        }

        @Test
        public void testSecond() {
            driver.get(baseUrl);
            driver.findElement(By.cssSelector("input[name='fio']")).click();
            driver.findElement(By.cssSelector("input[name='fio']")).clear();
            driver.findElement(By.cssSelector("input[name='fio']")).sendKeys("Zxc");
            driver.findElement(By.cssSelector("input[name='email']")).click();
            driver.findElement(By.cssSelector("input[name='email']")).clear();
            driver.findElement(By.cssSelector("input[name='email']")).sendKeys("qwe");
            driver.findElement(By.cssSelector("input[name='phone']")).click();
            driver.findElement(By.cssSelector("input[name='phone']")).clear();
            driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("+7 (111)");
            driver.findElement(By.cssSelector("input[name='city']")).click();
            driver.findElement(By.cssSelector("input[name='city']")).clear();
            driver.findElement(By.cssSelector("input[name='city']")).sendKeys("city1");
            driver.findElement(By.cssSelector("div.SelectItem__data_3u2ir.SelectItem__placeholder_3_ypX.SelectItem__isRequired_2J1mB.SelectItem__data_noWrap_FIC1K")).click();
            driver.findElement(By.cssSelector("div[name='form'] div.Header__header_3Teza")).click();
            assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", driver
                    .findElement(By.xpath("form div.Error__errorMessage_q8BBY")).getText());
            assertEquals("Введите корректный адрес эл. почты", driver
                    .findElement(By.xpath("form div.Row__row_AjrJL:nth-child(2) div.Error__errorMessage_q8BBY")).getText());
            assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9", driver
                    .findElement(By.xpath("form div.Row__row_AjrJL:nth-child(3) div.Error__errorMessage_q8BBY")).getText());
            assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис", driver
                    .findElement(By.xpath("form div.Row__row_AjrJL:nth-child(4) div.Error__errorMessage_q8BBY")).getText());
            assertEquals("Поле обязательное", driver
                    .findElement(By.xpath("form div.Row__row_AjrJL:last-child div.Error__errorMessage_q8BBY")).getText());
        }

    }

