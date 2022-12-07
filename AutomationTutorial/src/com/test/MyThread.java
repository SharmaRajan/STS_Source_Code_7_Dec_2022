package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MyThread implements Runnable {
	
	public static WebDriver driver;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args)  {
		
		MyThread myThread = new MyThread();
		
		Thread t1 = new Thread(myThread, "MyThread");
		
		t1.start();
		t1.run();
		
		try {
			t1.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//SignIn Button
		driver.findElement(By.id("signin")).click();
		
	}

	@Override
	public void run() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sharmajee\\Downloads\\Compressed\\Selenium Files\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();

		options.addArguments("start-maximized");

		options.addArguments("disable-infobars");

		options.addArguments("--disable-extensions");
		
		driver = new ChromeDriver(options);
		
		driver.get("http://poshanabhiyaan.gov.in/#/login/");
	}

}
