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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @Test
    public void testConvertTo()
    {
        WebElement from = driver.findElement(By.xpath(".//*[@id='from']"));
        from.clear();
        from.sendKeys(ParametersParser.getInstance().getTestConvertToParam());

        assertThat(driver.findElement(By.xpath(".//*[@id='to']")).getAttribute("value"), is(not("")));
    }

    @Test
    public void testCorrectNumFrom()
    {
        WebElement from = driver.findElement(By.xpath(".//*[@id='from']"));
        from.clear();
        from.sendKeys(ParametersParser.getInstance().getTestCorrectNumFromParam());

        assertThat(driver.findElement(By.xpath(".//*[@id='from']")).getText(), is(""));
    }

    @Test
    public void testCorrectNumTo()
    {
        WebElement from = driver.findElement(By.xpath(".//*[@id='to']"));
        from.clear();
        from.sendKeys(ParametersParser.getInstance().getTestCorrectNumToParam());

        assertThat(driver.findElement(By.xpath(".//*[@id='to']")).getText(), is(""));
    }

    @AfterClass
    public static void tearDown()
    {
        driver.quit();
    }
}