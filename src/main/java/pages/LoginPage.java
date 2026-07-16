package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.Select;

import utils.Log;

public class LoginPage {

    private WebDriver driver;
    private By textareaTextBox = By.xpath("//textarea[@name='my-textarea']");
    private By textinputTextBox = By.id("my-text-id");
    private By submitButton = By.xpath("//button[text()='Submit']");
    private By dropDownSelect = By.name("my-select"); 
    private By datePicker = By.name("my-date");

    public LoginPage(WebDriver driver) { 
        this.driver = driver;
    }

    public void enterTextAreaTextBox(String text) {
        driver.findElement(textareaTextBox).sendKeys(text); 
    }

    public void enterInputTextBox(String text) {
        driver.findElement(textinputTextBox).sendKeys(text);
    }

    //public void selectDropDownOption() {
    //Select select = new Select(driver.findElement(dropDownSelect));
    //select.selectByVisibleText("Two");
    //}
    public void selectDropDownOption(String option) {
    Select select = new Select(driver.findElement(dropDownSelect));
    select.selectByVisibleText(option);
    }

    public void selectCurrentDate() {
    driver.findElement(datePicker).click();
    String today = String.valueOf(java.time.LocalDate.now().getDayOfMonth());
    driver.findElement(By.xpath("//td[@class='day' and text()='" + today + "']")).click();
    }
    
    public void selectDate(int day) {
    driver.findElement(datePicker).click();
    driver.findElement(By.xpath("//td[@class='day' and text()='" + day + "']")).click();
    }

    public void clickSubmitButton() {
        Log.info("Clicking Loggin Button");
        driver.findElement(submitButton).click();

    }

}
