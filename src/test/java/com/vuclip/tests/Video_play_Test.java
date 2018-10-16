package com.vuclip.tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.vuclip.baselib.Baseclass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class Video_play_Test extends Baseclass {
	AppiumDriver<MobileElement> driver;
	WebDriverWait wait;
	String Video_search="Heart Attack";
	
	@BeforeTest
	public void setup()
	{
		start_server("127.0.0.1",4725);
		driver=getDriver();  // Returns android driver instance
	}
	
	@Test
	public void Home_page_validation()
	{
		//AppHomePage appHomePage=new AppHomePage(driver);
		
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(appHomePage.watch_text)); //Waiting till Home page loads and 
		//checking the text What do you like to watch more?
		Assert.assertTrue(appHomePage.Hindi_section.isDisplayed()); //Checking all section are displayed or not
		Assert.assertTrue(appHomePage.koran_section.isDisplayed());
		Assert.assertTrue(appHomePage.tamil_section.isDisplayed());
		Assert.assertTrue(appHomePage.telugu_section.isDisplayed());
	}
	
	@Test
	public void Search_video()
	{
		//VideoPage videoPage=new VideoPage(driver);
		appHomePage.Hindi_section.click();  // Clicking on Hindi section
		Assert.assertTrue(appHomePage.Hindi_section.isDisplayed()); // Checking if correct navigation to section is happening
		//wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(videoPage.vuclip_logo)); //Waiting till Vuclip logo is displayed on video page
		videoPage.searchVideoButton.click();
		videoPage.search_textBox.sendKeys(Video_search);  //Entering search text
		videoPage.searchbutton.click();
		Assert.assertTrue(videoPage.video.isDisplayed());  // checking whether video is displayed or not
		
	}
	
	@Test(dependsOnMethods="Search_video")
	public void play_pause_seek_video() throws InterruptedException
	{
		videoPage.video.click();  //clicking on video that needs to be played
		Assert.assertEquals(videoPage.video_title.getText(), Video_search); // Checking that searched video title present or not
		videoPage.video_play_btn.click();  //clicking on play button
		Thread.sleep(40000);  // waiting for 40 sec to keep video playing
		TouchAction ta=new TouchAction(driver);
		
		Dimension size=driver.manage().window().getSize();
		int x=(int)size.width/2;
		int y=(int)size.height/2;
		ta.press(PointOption.point(x, y)).release().perform();
		Thread.sleep(200);
		ta.press(PointOption.point(x, y)).release().perform();//double tapping at middle of screen to pause video
		
		Thread.sleep(10000);
		horizontal_swipe(driver, 0.4, 0.7);  // swipe to seek the video
		ta.press(PointOption.point(x, y)).release().perform();  //play the video
	}
	
	
	@AfterTest
	public void stop_appium_server()
	{
		stop_server();
	}
}
