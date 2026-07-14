package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Log;

public class LoginPage {

    private WebDriver driver;
    private By textareaTextBox = By.xpath("//textarea[@name='my-textarea']");
    private By textinputTextBox = By.id("my-text-id");
    private By submitButton = By.xpath("//button[text()='Submit']");

    public LoginPage(WebDriver driver) { 
        this.driver = driver;
    }

    public void enterTextAreaTextBox(String text) {
        driver.findElement(textareaTextBox).sendKeys(text); 
    }

    public void enterInputTextBox(String text) {
        driver.findElement(textinputTextBox).sendKeys(text);
    }

    public void clickSubmitButton() {
        Log.info("Clicking Loggin Button");
        driver.findElement(submitButton).click();

    }

}
