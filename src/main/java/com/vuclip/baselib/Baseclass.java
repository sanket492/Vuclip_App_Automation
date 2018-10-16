package com.vuclip.baselib;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vuclip.pages.AppHomePage;
import com.vuclip.pages.VideoPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Baseclass {
	
	AppiumDriverLocalService server;
	AppiumDriver<MobileElement> driver;
	public AppHomePage appHomePage;
	public VideoPage videoPage;
	
	/**
	 * Creates a Android driver session on appium server with capbilities defined 
	 * @return Android driver instance for mobile app
	 */
	public AppiumDriver<MobileElement> getDriver()
	{
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Motog2");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.vuclip.viu");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.vuclip.viu.ui.screens.MainActivity");
		
		driver=new AndroidDriver<MobileElement>(server.getUrl(),dc);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		appHomePage=new AppHomePage(driver);
		videoPage=new VideoPage(driver);
		return driver;
	}

	/**
	 * Starts a appium server at provided server IP and port
	 */
	public void start_server(String serverIP,int port)
	{
		AppiumServiceBuilder service=new AppiumServiceBuilder();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"); 
		String time=dtf.format(now);
		service.usingPort(port).withIPAddress(serverIP).withLogFile(new File("G:\\Appium_Training_Android_studio_project\\"+time+".txt")).build();
		server=AppiumDriverLocalService.buildService(service);
		server.start();
	}
	
	/**
	 * Stops running appium server instance
	 */
	public void stop_server()
	{
		server.stop();
    }
	
	
	/**
	 * Horizontal swipe action for seeking video
	 */	
	public void horizontal_swipe(AppiumDriver<MobileElement> driver,double d, double e)
	{
			Dimension size=driver.manage().window().getSize();
			int y=624;
			int start=(int) (size.width*d);
			int end=(int) (size.width*e);
			
			TouchAction touch=new TouchAction(driver);
		    touch.press(PointOption.point(start,y))
		    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
		    .moveTo(PointOption.point(end,y))
		    .release().perform();
			
	}
	
}

