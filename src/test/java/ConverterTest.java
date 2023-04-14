import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Title;

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
    @Title("Test case of convert money")
    public void testConvertTo()
    {
        WebElement from = driver.findElement(By.xpath(".//*[@id='from']"));

        for (String param : ParametersParser.getInstance().getTestConvertToParam())
        {
            from.clear();
            from.sendKeys(param);

            saveAllureScreenshot();
            assertThat(driver.findElement(By.xpath(".//*[@id='to']")).getAttribute("value"), is(not("")));
        }
    }

    @Test
    @Title("Test case of correct input in from-field")
    public void testCorrectNumFrom()
    {
        WebElement from = driver.findElement(By.xpath(".//*[@id='from']"));

        for (String param : ParametersParser.getInstance().getTestCorrectNumFromParam())
        {
            from.clear();
            from.sendKeys(param);

            saveAllureScreenshot();
            assertThat(driver.findElement(By.xpath(".//*[@id='from']")).getAttribute("value"), is(""));
        }
    }

    @Test
    @Title("Test case of correct input in to-field")
    public void testCorrectNumTo()
    {
        WebElement to = driver.findElement(By.xpath(".//*[@id='to']"));

        for (String param : ParametersParser.getInstance().getTestCorrectNumToParam())
        {
            to.clear();
            to.sendKeys(param);

            saveAllureScreenshot();
            assertThat(driver.findElement(By.xpath(".//*[@id='to']")).getAttribute("value"), is(""));
        }
    }

    @AfterClass
    public static void tearDown()
    {
        driver.quit();
    }
}
