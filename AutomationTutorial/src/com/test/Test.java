package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {
	
	public static WebDriver driver;
	
	WebDriver driver_;

	static WebDriver myDriver;

	public static void main(String[] args) throws Exception {

		startThread();	
		
		List<String[]> stringList = getList();
		
		for(int i = 0; i < stringList.size()/1000; i++) {
			String[] str = stringList.get(i);
			formFill(str[0],str[1],str[2],str[3],str[4],str[5]);
			System.out.println("S No. = " + str[0] + " ,Adult Male = " + str[1] + " ,Adult Female = " + str[2] +
					" , Child Male = " + str[3] + " ,Child Female = " + str[4] + " ,Sum = " + str[5]);
		}
		
	}
	
	
	public static List<String[]> getList() throws Exception {
		String line = "";  
		String splitBy = ",";  

		List<String[]> stringList = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Sharmajee\\Desktop\\Random Unique Values under 20 New.csv"));  
		
		while ((line = br.readLine()) != null)  { //returns a Boolean value    
			String[] employee = line.split(splitBy);    // use comma as separator
			stringList.add(employee);
		}  
//		System.out.println(stringList.size());
			br.close();
//			for(int i =0; i <stringList.size()/10000; i++) {
//					String[] str = stringList.get(i);
//					System.out.println("S No. = " + str[0] + " ,Adult Male = " + str[1] + " ,Adult Female = " + str[2] +
//							" , Child Male = " + str[3] + " ,Child Female = " + str[4] + " ,Sum = " + str[5]);
//			}
		
		return stringList;
		
	}
	
	public static void formFill(String sNo,String adultMale, String adultFemale, String childMale, String childFemale, String sum) {
		int val = 24;
		int var1 = 102;
		String str = "mat-option-";

		var activity = driver.findElement(By.id("mat-select-0"));// sendKeys("mat-option-22"); 22 to 100
		activity.click();
					
					
		var activitySelect = driver.findElement(By.id(str+ val));//  id = mat-option-22   =>      Activity on ECCE(Early childhood & Education) activities
		activitySelect.click();
					
		waitInSec(1);

		var theme = driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-hierarchy/div[1]/div/div/form/div/div[1]/div/div/div[1]/div[1]/div/div[3]/mat-form-field"));
		theme.click();

		var themeSelect = driver.findElement(By.id(str + var1));
		waitInSec(1);
		themeSelect.click();
					
					
		var fromDate = driver.findElement(By.id("mat-input-3"));
		fromDate.click();
					
		var fromDateSelect = driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-hierarchy/div[1]/div/div/form/div/div[1]/div/div/div[1]/div[3]/div/div[2]/div/div[1]/mat-form-field/div/div[1]/div[2]/mat-datepicker-toggle/button"));
		fromDateSelect.click();
					
		var dateSelect = driver.findElement(By.xpath("//*[@id=\"mat-datepicker-0\"]/div[2]/mat-month-view/table/tbody/tr[2]/td[3]"));
		dateSelect.click();

//		int min = 0, max = 20;
//					
//		int count1 = (int)Math.floor(Math.random()*(max-min+1)+min);
//		int count2 = (int)Math.floor(Math.random()*(max-min+1)+min);
//		int count3 = (int)Math.floor(Math.random()*(max-min+1)+min);
//		int count4 = (int)Math.floor(Math.random()*(max-min+1)+min);
//		int sum = 0;
//					
//		sum = count1 + count2 + count3 + count4;
					
		var maleAdult = driver.findElement(By.xpath("//*[@id=\"id1\"]/th[2]/input"));
		maleAdult.sendKeys(adultMale);
					
		var femaleAdult = driver.findElement(By.xpath("//*[@id=\"id1\"]/th[3]/input"));
		femaleAdult.sendKeys(adultFemale);


		var maleChild = driver.findElement(By.xpath("//*[@id=\"id2\"]/th[2]/input"));
		maleChild.sendKeys(childMale);
					
		var femaleChild = driver.findElement(By.xpath("//*[@id=\"id2\"]/th[3]/input"));
		femaleChild.sendKeys(childFemale);


		var totalParticipants = driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-hierarchy/div[1]/div/div/form/div/div[1]/div/div/div[2]/div[1]/div/div[2]/div/div[2]/input"));
		totalParticipants.sendKeys(sum);

		var submitBtn = driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-hierarchy/div[1]/div/div/form/div/div[2]/div/div[3]/div/div/button"));
		submitBtn.click();

		waitInSec(2);
		var anotherEle = driver.findElement(By.xpath("/html/body/app-dashboard/div/main/div/app-hierarchy/p-dialog[1]/div/div[1]/a"));
		waitInSec(2);
		
		anotherEle.click();
		waitInSec(1);
		
		System.out.println("Adult Male = " + adultMale + " Adult Female = " + adultFemale + " child Male = " + childMale + " Child Female = " + 
				childFemale + " Sum = " + sum);

	}
	
	
	public static void waitInSec(int sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
			} catch (InterruptedException e) {
			  Thread.currentThread().interrupt();
			}
	}
	
	
	public static void startThread() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sharmajee\\Downloads\\Compressed\\Selenium Files\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();

		options.addArguments("start-maximized");

		options.addArguments("disable-infobars");

		options.addArguments("--disable-extensions");
		
		driver = new ChromeDriver(options);
		
		driver.get("http://poshanabhiyaan.gov.in/#/login/");
		
		//UserName
				driver.findElement(By.id("mat-input-0")).sendKeys("MoW&CD-NAWADA");
				
		//Password
		driver.findElement(By.id("mat-input-1")).sendKeys("MoW&CD-NAWADA");
				
		//SignIn Button
		var signIn = driver.findElement(By.id("signin"));
				
		waitInSec(2);
				
		signIn.click();
				
		waitInSec(3);
		
		var okBtn = driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/div/div[3]/button"));
		okBtn.click();
		
	}
	


}
