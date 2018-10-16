package com.vuclip.pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Locators for objects on video page of Viu app
 */
public class VideoPage {
	
	@AndroidFindBy(id="com.vuclip.viu:id/iv_logo")
	public MobileElement vuclip_logo;
	
	@AndroidFindBy(id="com.vuclip.viu:id/iv_search")
	public MobileElement searchVideoButton;
	
	@AndroidFindBy(id="com.vuclip.viu:id/search_box")
	public MobileElement search_textBox;
	
	@AndroidFindBy(id="com.vuclip.viu:id/search_icon")
	public MobileElement searchbutton;
	
	@AndroidFindBy(xpath="//com.vuclip.viu.ui.customviews.ViuMultiDirectionalScrollView[contains(@content-desc,'Movies')]/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView")
	public MobileElement video;
	
	@AndroidFindBy(id="com.vuclip.viu:id/v_detail_episode_name")
	public MobileElement video_title;
	
	
	@AndroidFindBy(id="com.vuclip.viu:id/v_detail_play")
	public MobileElement video_play_btn;
	
	
	public VideoPage(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

}
