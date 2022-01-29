package main

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.annotations.*
import utils.appPath
import java.net.URL
import java.util.concurrent.TimeUnit

lateinit var driver:AppiumDriver<MobileElement>

open class BaseClass {

    protected val testMethods = TestMethods()
    protected val generalTestFunctions = GeneralTestFunctions()

    @BeforeSuite
    @Parameters(
        value = ["paramPlatformVersion", "paramDeviceName",
            "paramPlatformName", "paramTimeToDelay", "paramUDID"]
    )
    fun setupDriver(
        paramPlatformVersion: String, paramDeviceName: String,
        paramPlatformName: String, paramTimeToDelay: Long,
        paramUDID: String
    ) {
        val url = URL("http://127.0.0.1:4723/wd/hub")
        val caps = DesiredCapabilities()

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, paramPlatformName) //название платформы
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, paramPlatformVersion) // версия ОС
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, paramDeviceName) // имя устройства
        caps.setCapability(MobileCapabilityType.NO_RESET, true) //не сбрасывать приложение в 0 при новом запуске
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "7200")
        // caps.setCapability(MobileCapabilityType.UDID,paramUDID)

        when (paramPlatformName) {
            "Android" -> {
                caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sportmaster.app.handh.dev")
                caps.setCapability(
                    AndroidMobileCapabilityType.APP_ACTIVITY,
                    "ru.sportmaster.app.presentation.start.StartActivity"
                )
                caps.setCapability(MobileCapabilityType.APP, appPath.fullAppLocalPathAndroid)
                driver = AndroidDriver(url, caps) // установка драйвера и приложения на Android device
            }
            "iOS" -> {
                caps.setCapability(MobileCapabilityType.APP, appPath.fullLocalAppLocalPathIOS)
                driver = IOSDriver(url, caps) //установка драйвера для iOS
            }
        }


        driver.manage().timeouts().implicitlyWait(paramTimeToDelay, TimeUnit.SECONDS)
        println("driver install success")

        //проверка наличия онбординга на экране и прохождение до главной минуя авторизацию (если онбординг найден)
        generalTestFunctions.checkOnbordAndPassTrue(paramPlatformName)

        driver.closeApp()
    }

        //driver.closeApp() //для iOS 15+ не запустит
        //driver.resetApp() //длф ios 15+


        @AfterSuite
        fun end(){
            println("тест окончен")
            driver.quit()
        }

        @BeforeClass
        @Parameters(
            value = ["paramPlatformVersion", "paramDeviceName",
                "paramPlatformName", "paramTimeToDelay", "paramUDID"]
        )
        // заново инициализировать драйвер (для андройда)
        fun beforeClass(
            paramPlatformVersion: String, paramDeviceName: String,
            paramPlatformName: String, paramTimeToDelay: Long,
            paramUDID: String
        ){
            val url = URL("http://127.0.0.1:4723/wd/hub")
            val caps = DesiredCapabilities()

            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, paramPlatformName) //название платформы
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, paramPlatformVersion) // версия ОС
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, paramDeviceName) // имя устройства
            caps.setCapability(MobileCapabilityType.NO_RESET, true) //не сбрасывать приложение в 0 при новом запуске
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "7200")
            // caps.setCapability(MobileCapabilityType.UDID,paramUDID)

            when (paramPlatformName) {
                "Android" -> {
                    caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ru.sportmaster.app.handh.dev")
                    caps.setCapability(
                        AndroidMobileCapabilityType.APP_ACTIVITY,
                        "ru.sportmaster.app.presentation.start.StartActivity"
                    )
                    caps.setCapability(MobileCapabilityType.APP, appPath.fullAppLocalPathAndroid)
                    driver = AndroidDriver(url, caps) // установка драйвера и приложения на Android device
                }
                "iOS" -> {
                    caps.setCapability(MobileCapabilityType.APP, appPath.fullLocalAppLocalPathIOS)
                    driver = IOSDriver(url, caps) //установка драйвера для iOS
                }
            }

            driver.manage().timeouts().implicitlyWait(paramTimeToDelay, TimeUnit.SECONDS)
            println("driver install success")


            //generalTestFunctions.initDriver() - ????не принимает long параметр

            //закрыть приложение
            driver.closeApp()
            TimeUnit.SECONDS.sleep(2)
        }

        @AfterClass
        fun afterClass(){
            driver.quit() //закрыть сессию драйвера
        }

        @BeforeMethod
        @Parameters(
            value = ["paramPlatformVersion", "paramDeviceName",
                "paramPlatformName", "paramTimeToDelay", "paramUDID"]
        )
        fun beforeMethod(){
            driver.launchApp() //запуск приложения
            TimeUnit.SECONDS.sleep(5)

        }

        @AfterMethod
        fun afterMethod(){
            //закрыть приложение
            driver.closeApp()
            TimeUnit.SECONDS.sleep(2)

        }

    }
