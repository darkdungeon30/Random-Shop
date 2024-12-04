// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class Finish_order {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void finishorder() throws InterruptedException {
    driver.get("http://localhost:5173/");
    driver.manage().window().maximize();

    driver.findElement(By.cssSelector("select")).click();
    {
      WebElement dropdown = driver.findElement(By.cssSelector("select"));
      dropdown.findElement(By.xpath("//option[. = 'Admin']")).click();
    }
    driver.findElement(By.cssSelector("option:nth-child(2)")).click();
    driver.findElement(By.cssSelector("input:nth-child(1)")).sendKeys("admin123");
    driver.findElement(By.cssSelector("input:nth-child(2)")).sendKeys("admin123");
    driver.findElement(By.cssSelector("input:nth-child(1)")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector("button")).click();
    Thread.sleep(500);
    {
      WebElement element = driver.findElement(By.cssSelector("button"));
      Thread.sleep(500);
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    driver.findElement(By.cssSelector(".el-sub-menu:nth-child(4) > .el-sub-menu__title")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector(".el-sub-menu:nth-child(4) .el-menu-item:nth-child(2)")).click();
    Thread.sleep(500);
    driver.findElement(By.cssSelector(".el-table__row:nth-child(1) .el-button:nth-child(1) > span")).click();
    Thread.sleep(500);
  }
}
