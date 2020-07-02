import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Form {
    @Test
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hariprakash.k\\Downloads\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://formy-project.herokuapp.com/form");

        submitForm(driver);

        waitForAlertMsg(driver);

        Assert.assertEquals(assertAlertMsg(driver), "The form was successfully submitted!");

        driver.quit();
    }

    public static void submitForm(WebDriver driver) {
        driver.findElement(By.id("first-name")).sendKeys("Hari");
        driver.findElement(By.id("last-name")).sendKeys("Prakash K");
        driver.findElement(By.id("job-title")).sendKeys("Test analyst");
        driver.findElement(By.id("radio-button-2")).click();
        driver.findElement(By.id("checkbox-1")).click();
        driver.findElement(By.cssSelector("#select-menu option[value='1']")).click();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.now();
        driver.findElement(By.id("datepicker")).sendKeys(dtf.format(date));
        driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary")).click();
    }

    public static void waitForAlertMsg(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver , 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));
    }

    public static String assertAlertMsg(WebDriver driver){
        return driver.findElement(By.cssSelector(".alert.alert-success")).getText();
    }
}
