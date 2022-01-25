package main

import constructor_classes.LocatorsConstructor
import constructor_classes.locatorsTypes
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import locators.AuthScreenLocators
import locators.BottomBarLocators
import locators.CatalogScreenLocators
import locators.SplashScreenLocators
import org.openqa.selenium.WebDriverException
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
        caps.setCapability(MobileCapabilityType.NO_RESET, false) //не сбрасывать приложение в 0 при новом запуске
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


        //проверка наличия онбординга на экране
        if (testMethods.availableElement(locatorType = locatorsTypes.Id, locator = SplashScreenLocators().onbording.androidId))
        {
            //close boarding
            testMethods.clickToElement(locatorType = locatorsTypes.xpath,
                    locator = SplashScreenLocators().exitButtonOnSplashScreen.androidXpath)
            println("онбординг закрыт")

            //close authorization
            testMethods.clickToElement(locatorType = locatorsTypes.xpath,
            locator = AuthScreenLocators().closeButtonAuth.androidXpath)
            println("экран авторизации закрыт без авторизации")

            TimeUnit.SECONDS.sleep(3)

            //кнопка разрешения геопозиции
            testMethods.clickToElement(locatorType = locatorsTypes.Id,
                locator = CatalogScreenLocators().permissionAllow.androidId)
            println("определение геопозиции разрешено")

            TimeUnit.SECONDS.sleep(3)

            //кнопка подсказки города
            testMethods.clickToElement(locatorType = locatorsTypes.Id,
                locator = CatalogScreenLocators().chooseCityFromHelper.androidId
            )
            println("выбран город из подсказки")

            TimeUnit.SECONDS.sleep(4)

        } else {
            testMethods.clickToElement(locatorType = locatorsTypes.Id,
            locator = BottomBarLocators().dashboard.androidId)
        }
        // прохождение до главной минуя авторизацию (если онбординг найден)
        //driver.closeApp() //для iOS 15+ не запустит
        //driver.resetApp() //длф ios 15+


        @AfterSuite
        fun end() {
            println("тест окончен")
            driver.quit()
        }

        /*@BeforeClass
    fun beforeClass(){
        // заново инициилизировать драйвер (для андройда)
        //закрыть приложение
    }

    @AfterClass
    fun afterClass(){
        driver.quit() //закрыть сессию драйвера
    }

    @BeforeMethod
    fun beforeMethod(){
        driver.launchApp() //запуск приложения
        TimeUnit.SECONDS.sleep(4)

    }

    @AfterMethod
    fun afterMethod(){
        //закрыть приложение
    }
*/
    }
}