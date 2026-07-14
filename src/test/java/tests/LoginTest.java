package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest { 
    
    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws IOException{

        String filePath = System.getProperty("user.dir")+"/testdata/TestData.xlsx"; 
        ExcelUtils.loadExcel(filePath, "Sheet1"); 
        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount-1][2]; 
        
        for(int i=1; i<rowCount; i++){

            data[i-1][0] = ExcelUtils.getCellData(i, 0); // Test Area Textbox 
            data[i-1][1] = ExcelUtils.getCellData(i, 1); // Input Textbox
        }
        ExcelUtils.closeExcel();
        return data;
    }

    @DataProvider(name="LoginData2") 
    public Object[][] getData(){     // this is alternative data coming from script vs external file but still Data Driven Testing

        return new Object[][]{
            {"1st) Helloooooooooooooo Text Are Textbox", "1st) Hello Input Textbox"},
            {"2nd) Hello Text Are Textbox", "2nd) Hello Input Textbox"},
            {"3rd) Hello Text Are Textbox", "3rd) Hello Input Textbox"},
        };
    }
    
   
   @Test(dataProvider = "LoginData2") // tells test to use data provider we created above to use for this test run
    //public void testValidLogin() {
    public void testValidLogin(String textAreaTextbox, String inputTextbox) { 
        Log.info("Starting login test...");
        test = ExtentReportManager.createTest("LOGIN TEST" + textAreaTextbox + " & " + inputTextbox); 
        test.info("NAVIGATING TO URL");
        LoginPage loginPage = new LoginPage(driver);

        Log.info("Entering text into TextboxArea TextBox");
        test.info("ENTERING TEXT INTO TextboxArea Texbox");
        loginPage.enterTextAreaTextBox(textAreaTextbox); // Data Driven testing

        Log.info("Entering text into Textbox");
        test.info("ENTERING TEXT INTO Textbox");
        //loginPage.enterInputTextBox("INPUT TEXTBOX"); // Data Driver testing
        loginPage.enterInputTextBox(inputTextbox);

        test.info("CLICKING ON SUBMIT BUTTON");
        loginPage.clickSubmitButton();

        System.out.println("Title of the page is :" + driver.getTitle());
        Log.info("Verifying page title");
        test.info("VERYFYING PAGE TITLE");
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Web form - target page");
        test.pass("LOGIN SUCCESSFUL");

    }

}