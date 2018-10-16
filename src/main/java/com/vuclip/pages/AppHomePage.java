package com.vuclip.pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


/**
 * Locators for objects on Home page of Viu app
 */
public class AppHomePage {
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='What do you like to watch more?']")
	public MobileElement watch_text;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Korean']")
	public MobileElement koran_section;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tamil']")
	public MobileElement tamil_section;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Telugu']")
	public MobileElement telugu_section;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Hindi']")
	public MobileElement Hindi_section;
	
	public AppHomePage(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

}
