// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class Create_order {
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
  public void creatorder() throws InterruptedException {
    driver.get("http://localhost:5173/");
    driver.manage().window().setSize(new Dimension(876, 680));
    driver.findElement(By.xpath("//input")).click();
    driver.findElement(By.xpath("//input[2]")).sendKeys("A1");
    driver.findElement(By.xpath("//input")).sendKeys("A1");
    Thread.sleep(500);
    driver.findElement(By.xpath("//button[contains(.,\'用户登录\')]")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//div[2]/div/div/div/div/img")).click();
    Thread.sleep(500);
    driver.findElement(By.xpath("//span[contains(.,\'购买\')]")).click();
    Thread.sleep(500);
    driver.findElement(By.xpath("//textarea")).click();
    Thread.sleep(500);
    driver.findElement(By.xpath("//textarea")).sendKeys("kuai");
    Thread.sleep(500);
    driver.findElement(By.cssSelector(".el-button--primary")).click();
    Thread.sleep(500);
  }
}
