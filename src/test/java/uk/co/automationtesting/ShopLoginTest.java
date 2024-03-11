package uk.co.automationtesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopHomepage;
import pageObjects.ShopLoginPage;
import pageObjects.ShopYourAccount;

@Listeners(resources.Listeners.class)

public class ShopLoginTest extends Hooks {

	public ShopLoginTest() throws IOException {
		super();
	}

	@Test
	public void endToEndTest() throws InterruptedException, IOException {

		ExtentManager.log("Starting ShopLoginTest...");

		// creating an object of the automationtesting.co.uk webpage
		Homepage home = new Homepage();
		ExtentManager.pass("Have successfully reached store homepage");

		// handles cookie prompt
		home.getCookie().click();

		home.getTestStoreLink().click();

		// creating an object of the test store homepage
		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getLoginBtn().click();

		FileInputStream workbookLocation = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\credentials.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(workbookLocation);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Row row1 = sheet.getRow(1);
		Cell cellR1C0 = row1.getCell(0);
		Cell cellR1C1 = row1.getCell(1);
		
		String emailRow1 = cellR1C0.toString();
		String passwordRow1 = cellR1C1.toString();
		
		System.out.println(emailRow1);
		System.out.println(passwordRow1);
		
		ShopLoginPage loginPage = new ShopLoginPage();
		loginPage.getEmail().sendKeys(emailRow1);
		loginPage.getPassword().sendKeys(passwordRow1);
		loginPage.getSubmitBtn().click();
		
		ShopYourAccount yourAcc = new ShopYourAccount();

		try {
			yourAcc.getSignOut().click();
			ExtentManager.pass("User has signed in");
		} catch (Exception e) {
			ExtentManager.fail("User could not sign in");
			Assert.fail();
		}
		
		
		
		Row row2 = sheet.getRow(2);
		Cell cellR2C0 = row2.getCell(0);
		Cell cellR2C1 = row2.getCell(1);
		
		String emailRow2 = cellR2C0.toString();
		String passwordRow2 = cellR2C1.toString();
		
		System.out.println("emailRow2 " + emailRow2);
		System.out.println("passwordRow2 " + passwordRow2);
		
		loginPage.getEmail().sendKeys(emailRow2);
		loginPage.getPassword().sendKeys(passwordRow2);
		loginPage.getSubmitBtn().click();
		
		try {
			yourAcc.getSignOut().click();
			ExtentManager.pass("User has signed in");
		} catch (Exception e) {
			ExtentManager.fail("User could not sign in");
			Assert.fail();
		}
		
		
	}

}
