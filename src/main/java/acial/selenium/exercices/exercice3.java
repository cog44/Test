package acial.selenium.exercices;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class exercice3 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
	    @DataProvider(name = "salarie")
	    public Object[][] dataProviderMethod() {
	        return new Object[][] { { "ole", "gunnar", "3550"}, { "erling", "halang", "3566" } };
	    }
  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	  
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\cog\\Desktop\\Katalon\\chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Test (priority=1)
  public void testLogin() throws Exception {
    driver.get("http://www.universitedutest.com/OrangeHRM/");
    driver.findElement(By.id("txtUsername")).clear();
    driver.findElement(By.id("txtUsername")).sendKeys("Admin");
    driver.findElement(By.id("txtPassword")).clear();
    driver.findElement(By.id("txtPassword")).sendKeys("Nantes$2020");
    driver.findElement(By.id("btnLogin")).click();
     	
 }
  @Test (dataProvider = "salarie", priority=2)
  public void testAjouterEmploye(String nom , String prenom , String id) throws Exception {
	driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']/b")).click();
    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
    driver.findElement(By.id("menu_pim_addEmployee")).click();
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys(nom);
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys(prenom);
    driver.findElement(By.id("employeeId")).clear();
    driver.findElement(By.id("employeeId")).sendKeys(id);
    driver.findElement(By.id("btnSave")).click();
    driver.findElement(By.id("btnSave")).click();
    driver.findElement(By.id("personal_optGender_1")).click();
    new Select(driver.findElement(By.id("personal_cmbNation"))).selectByValue("134");
    new Select(driver.findElement(By.id("personal_cmbMarital"))).selectByValue("Married");
    driver.findElement(By.id("personal_DOB")).clear();
    driver.findElement(By.id("personal_DOB")).sendKeys("1970-09-14");
    driver.findElement(By.id("btnSave")).click();
  }
  

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

