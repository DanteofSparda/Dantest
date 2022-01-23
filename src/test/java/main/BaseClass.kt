package main

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test
import utils.appPath
import java.net.URL
import java.util.concurrent.TimeUnit

open class BaseClass {
    lateinit var driver:AppiumDriver<MobileElement>

    @BeforeSuite
    fun setupDriver(){


        val url = URL( "http://127.0.0.1:4723/wd/hub")
        val caps = DesiredCapabilities()


        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android") //название платформы
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11") // версия ОС
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel API 30") // имя устройства
        //caps.setCapability(MobileCapabilityType.NO_RESET,true) //не сбрасывать приложение в 0 при новом запуске
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"7200")
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sportmaster.app.handh.dev")
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ru.sportmaster.app.presentation.start.StartActivity")
        caps.setCapability(MobileCapabilityType.APP, appPath.fullAppLocalPathAndroid)//"/Users/Dante/sportmaster-4.0.13.5605_dev_beta.apk")
        // caps.setCapability(MobileCapabilityType.UDID,"")

        driver = AndroidDriver(url,caps) // установка драйвера и приложения на Android device
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS)
    }

    @AfterSuite
    fun end(){
        println("тест окончен")
        driver.quit()
    }


}