import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ConverterTest
{
    static WebDriver driver;

    @BeforeClass
    public static void setUp()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://www.sberbank.ru/ru/person");
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    protected byte[] saveAllureScreenshot()
    {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    @Title("Test case of convert to field")
    public void testConvertTo()
    {
        WebElement from = driver.findElement(By.xpath(".//*[@id='from']"));
        from.clear();
        from.sendKeys(ParametersParser.getInstance().getTestConvertToParam());

        saveAllureScreenshot();
        assertThat(driver.findElement(By.xpath(".//*[@id='to']")).getAttribute("value"), is(not("")));
    }

    @Test
    @Title("Test case of correct input in from field")
    public void testCorrectNumFrom()
    {
        WebElement from = driver.findElement(By.xpath(".//*[@id='from']"));
        from.clear();
        from.sendKeys(ParametersParser.getInstance().getTestCorrectNumFromParam());

        saveAllureScreenshot();
        assertThat(driver.findElement(By.xpath(".//*[@id='from']")).getText(), is(""));
    }

    @Test
    @Title("Test case of correct input in to field")
    public void testCorrectNumTo()
    {
        WebElement from = driver.findElement(By.xpath(".//*[@id='to']"));
        from.clear();
        from.sendKeys(ParametersParser.getInstance().getTestCorrectNumToParam());

        saveAllureScreenshot();
        assertThat(driver.findElement(By.xpath(".//*[@id='to']")).getText(), is(""));
    }

    @AfterClass
    public static void tearDown()
    {
        driver.quit();
    }
}