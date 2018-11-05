import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SecondTest extends BaseRuner {

    @Test
    public void test1() {
        // создаем wait на 10 секунд
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("https://www.google.ru/");
        driver.findElement(By.name("q")).sendKeys("мобайл тинькофф");
        driver.findElements(By.xpath("//ul[@role='listbox']/li"));

        //ожидание, игнорирующее StaleElementReferenceException
        wait
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Что-то пошло не так...")
                .pollingEvery(Duration.ofMillis(500))
                .until(d -> {
                    //список поисковой выдачи
                    By listItems = By.xpath("//ul[@role='listbox']/li[@role='presentation']//div[@class='sbqs_c']/b");
                    List<WebElement> elements = driver.findElements(listItems);
                    for (WebElement el : elements) {
                        System.out.println(el.getText());
                        //из списка вариантов дожиаемся появления нужного, кликаем
                        if (el.getText().equals(" тарифы")) el.click();
                        break;
                    }
                    //Ожидание появления заголовка
                    return d.getTitle().equals("Тарифы Тинькофф Мобайл");

                });


        String tinkoffTariffs = "https://www.tinkoff.ru/mobile-operator/tariffs/";

        wait.until(d -> xpathSearcherByText(tinkoffTariffs).size() > 0);
        xpathSearcherByText(tinkoffTariffs).get(0).click();

        //сайт открывается в новом окне, явно переключаемся к окну с заголовком  "Работа с Тинькофф"
        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                System.out.println(d.getTitle());
                check = d.getTitle().equals("Тарифы Тинькофф Мобайл");
            }
            return check;
        });

        //переключаемся к первой попавшейся вкладке (а у нас она теперь одна)
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
        //закрываем активную вкладку
        driver.close();
    }

    @Test
    public void test2() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.xpath("//div[@name='mvnoRegionConfirmation']//div[@class='MvnoRegionConfirmation__options_3M1tn']//span[@class='MvnoRegionConfirmation__option_3mrvz MvnoRegionConfirmation__optionAgreement_3M5qT']")).click();

        assertEquals("Москва и Московская область", driver
                .findElement(By.xpath("//div[@name='mvnoRegionConfirmation']//div[@class='MvnoRegionConfirmation__title_3WFCP']")).getText());

        driver.navigate().refresh();

        assertEquals("Москва и Московская область", driver
                .findElement(By.xpath("//div[@name='mvnoRegionConfirmation']//div[@class='MvnoRegionConfirmation__title_3WFCP']")).getText());

        String defMoscow = driver.findElement(By.xpath("//form[@class='ui-form']//h3[@class='ui-title ui-title_form ui-title_center " +
                "ui-title_size_s MobileOperatorFormFieldTitle__title_2awZp mobileOperatorProductCalculatorSchema__amountTitle_6kgKn']")).getText();

        driver.findElement(By.xpath("//select[@name='internet']/following-sibling::div[@class='ui-select_close ui-select_changed']")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Безлимитный интернет')]")).click();
        driver.findElement(By.xpath("//select[@name='calls']/following-sibling::div[@class='ui-select_close ui-select_changed']")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Безлимитные минуты')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Режим модема')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Безлимитные SMS')]")).click();

        String fullMoscow = driver.findElement(By.xpath("//form[@class='ui-form']//h3[@class='ui-title ui-title_form ui-title_center " +
                "ui-title_size_s MobileOperatorFormFieldTitle__title_2awZp mobileOperatorProductCalculatorSchema__amountTitle_6kgKn']")).getText();

        driver.findElement(By.xpath("//div[@name='mvnoRegionConfirmation']//div[@class='MvnoRegionConfirmation__title_3WFCP']")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Краснодар')]")).click();

        String defKrasnodar = driver.findElement(By.xpath("//form[@class='ui-form']//h3[@class='ui-title ui-title_form ui-title_center " +
                "ui-title_size_s MobileOperatorFormFieldTitle__title_2awZp mobileOperatorProductCalculatorSchema__amountTitle_6kgKn']")).getText();

        assertNotEquals(defMoscow,defKrasnodar);

        driver.findElement(By.xpath("//select[@name='internet']/following-sibling::div[@class='ui-select_close ui-select_changed']")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Безлимитный интернет')]")).click();
        driver.findElement(By.xpath("//select[@name='calls']/following-sibling::div[@class='ui-select_close ui-select_changed']")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Безлимитные минуты')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Режим модема')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Безлимитные SMS')]")).click();

        String fullKrasnodar = driver.findElement(By.xpath("//form[@class='ui-form']//h3[@class='ui-title ui-title_form ui-title_center " +
                "ui-title_size_s MobileOperatorFormFieldTitle__title_2awZp mobileOperatorProductCalculatorSchema__amountTitle_6kgKn']")).getText();

        assertEquals(fullMoscow,fullKrasnodar);

    }

    @Test
    public void test3(){
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

        driver.findElement(By.xpath("//select[@name='internet']/following-sibling::div[@class='ui-select_close ui-select_changed']")).click();
        driver.findElement(By.xpath("//span[contains(text(), '0 ГБ')]")).click();
        driver.findElement(By.xpath("//select[@name='calls']/following-sibling::div[@class='ui-select_close ui-select_changed']")).click();
        driver.findElement(By.xpath("//select[@name='calls']/../following-sibling::div/div[@class='ui-dropdown-field-list__item']")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Мессенджеры')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Социальные сети')]")).click();

        assertEquals("Общая цена: 0 \u20BD", driver.findElement(By.xpath("//form[@class='ui-form']//h3[@class='ui-title ui-title_form ui-title_center " +
                "ui-title_size_s MobileOperatorFormFieldTitle__title_2awZp mobileOperatorProductCalculatorSchema__amountTitle_6kgKn']")).getText());

        driver.findElement(By.xpath("//button[@class='Button__button_ZsAp- BlockingButton-theme__button_1mhBY Button__button_relative_3aJsS " +
                "Button__button_color_black_3jFBq Button__button_disabled_19Ety Button__button_rounded_1Eg4W Button__button_size_xxl_1_2X-']"))
                .isEnabled();

    }

    //универсальный xpath локатор, вернет все элементы, содержащие текст
    private List<WebElement> xpathSearcherByText(String searchText) {
        String xpath = String.format("//*[contains(text(),'%s')]", searchText);
        return driver.findElements(By.xpath(xpath));

    }
}