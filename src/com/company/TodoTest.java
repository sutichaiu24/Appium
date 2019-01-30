package com.company;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.Before;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


import static org.junit.Assert.assertEquals;

public class TodoTest {
   AndroidDriver driver;
   private WebElement createTaskButton;
   private WebElement displayLabel;
   private WebElement textView;
   private WebElement textbox;
   private WebElement tasklabel;

   @Before
    public void setUp() throws  Exception{
       DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability("deviceName","Moto" );
       capabilities.setCapability("platformName","Android");
       capabilities.setCapability("platformVersion","7.0");
       capabilities.setCapability("app","/Users/sudhichaiungsuthornrungsi/Downloads/app-release.apk");


       driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
   }
   @Test
    public void testOpenApp() throws  Exception{
       displayLabel = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView"));
       textView = driver.findElement(By.id("dime.android.todo:id/empty_list"));

       assertEquals("Move along, nothing to see here!",textView.getText().toString());
       assertEquals("To-Do List",displayLabel.getText().toString());
   }

   @Test
    public void testCreateTask() throws Exception{

       //Arrange
       String taskname = "task2";

       //Act
       createTaskButton = driver.findElement(By.id("dime.android.todo:id/new_todo"));
       createTaskButton.click();

       textbox = driver.findElement(By.id("dime.android.todo:id/txt_name"));
       textbox.sendKeys(taskname);

       driver.findElement(By.id("dime.android.todo:id/save")).click();
       tasklabel = driver.findElement(By.id(("dime.android.todo:id/task_name")));

       //Assert
       assertEquals(taskname,tasklabel.getText().toString());

   }

   @Test
    public void testDeleteTask() throws Exception{
        testCreateTask();

        new TouchAction(driver)
               .longPress(ElementOption.point(100,330))
               .moveTo(ElementOption.point(400,330))
               .release()
               .perform();

      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

      textView = driver.findElement(By.id("dime.android.todo:id/empty_list"));

      assertEquals("Move along, nothing to see here!",textView.getText().toString());

   }
}
