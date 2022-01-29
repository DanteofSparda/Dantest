package main

import constructor_classes.locatorsTypes
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.remote.AndroidMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import locators.*
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.AssertJUnit
import utils.appPath
import java.net.URL
import java.util.concurrent.TimeUnit

class GeneralTestFunctions {

    //авторизация пользователя
    fun authorization(phoneNumber: String, smsNumber: String, paramPlatformName: String) {
        TestMethods().clickToElement(
            locatorType = BottomBarLocators(paramPlatformName).locatorTypeProfileButton,
            locator = BottomBarLocators(paramPlatformName).profileButton.id
        )
        println("переход в личный кабинет")
        TimeUnit.SECONDS.sleep(3)


        //click on button signIn
        TestMethods().clickToElement(
            locatorType = EnterScreenLocators(paramPlatformName).locatorTypeButtonSignIn,
            locator = EnterScreenLocators(paramPlatformName).buttonSignIn.id
        )

        //ввод в поле номера телефона
        TestMethods().inputTextInField(
            locatorType = AuthScreenLocators(paramPlatformName).locatorTypeEditTextPhone,
            locator = AuthScreenLocators(paramPlatformName).editTextPhone.id,
            inputText = phoneNumber
        )
        println("телефон введен")

        //кнопка "получить смс"
        TestMethods().clickToElement(
            locatorType = AuthScreenLocators(paramPlatformName).locatorTypeButtonGetCode,
            locator = AuthScreenLocators(paramPlatformName).buttonGetCode.id
        )
        println("кнопка на получение смс нажата")

        //ввод в поле sms
        TestMethods().inputTextInField(
            locatorType = AuthScreenLocators(paramPlatformName).locatorTypePinCodeEditText,
            locator = AuthScreenLocators(paramPlatformName).pinCodeEditText.id,
            inputText = smsNumber
        )
        println("смс введено")

        TimeUnit.SECONDS.sleep(10)
    }

    //проверка на авторизованность (если true, то неавторизован
    fun authUserDetect(paramPlatformName: String): Boolean {
        //кнопка личный кабинет
        TestMethods().clickToElement(
            locatorType = BottomBarLocators(paramPlatformName).locatorTypeProfileButton,
            locator = BottomBarLocators(paramPlatformName).profileButton.id
        )

        TimeUnit.SECONDS.sleep(3)

        //проверка на доступность кнопки "войти"
        return TestMethods().availableElement(
            locatorType = EnterScreenLocators(paramPlatformName).locatorTypeButtonSignIn,
            locator = EnterScreenLocators(paramPlatformName).buttonSignIn.id
        )

    }

    //разлогин пользователя
    fun logout(paramPlatformName: String) {
        //кнопка личный кабинет
        TestMethods().clickToElement(
            locatorType = BottomBarLocators(paramPlatformName).locatorTypeProfileButton,
            locator = BottomBarLocators(paramPlatformName).profileButton.id
        )
        println("переход в личный кабинет")

        TimeUnit.SECONDS.sleep(2)

        //нажимаем на карандашик
        TestMethods().clickToElement(
            locatorType = ProfileScreenLocators(paramPlatformName).locatorTypeButtonEditProfile,
            locator = ProfileScreenLocators(paramPlatformName).buttonEditProfile.id
        )
        println("переход на экран редактирования данных пользователя")

        TimeUnit.SECONDS.sleep(3)

        //прокручиваем экран к низу
        TestMethods().swipeOnScreen(
            startCordX = 500,
            startCordY = 1300,
            moveCordX = 500,
            moveCordY = 750
        )

        //нажимаем на кнопку разлогина
        TestMethods().clickToElement(
            locatorType = ProfileScreenLocators(paramPlatformName).locatorTypeButtonLogout,
            locator = ProfileScreenLocators(paramPlatformName).buttonLogout.id
        )
        println("кнопка разлогина нажата")

        TimeUnit.SECONDS.sleep(3)

        AssertJUnit.assertTrue(
            "Неуспешный разлогин", TestMethods().availableElement(
                locatorType = EnterScreenLocators(paramPlatformName).locatorTypeButtonSignIn,
                locator = EnterScreenLocators(paramPlatformName).buttonSignIn.id
            )
        )
        println("Успешный разлогин")
    }

    fun checkOnbordAndPassTrue(paramPlatformName: String) {
        //проверка наличия онбординга на экране и прохождение до главной минуя авторизацию (если онбординг найден)
        if (TestMethods().availableElement(
                locatorType = SplashScreenLocators(paramPlatformName).locatorTypeExitButtonOnSplash,
                locator = SplashScreenLocators(paramPlatformName).exitButtonOnSplash.id
            )
        ) {
            //close boarding
            TestMethods().clickToElement(
                locatorType = SplashScreenLocators(paramPlatformName).locatorTypeExitButtonOnSplash,
                locator = SplashScreenLocators(paramPlatformName).exitButtonOnSplash.id
            )
            println("онбординг закрыт")

            //close authorization
            TestMethods().clickToElement(
                locatorType = AuthScreenLocators(paramPlatformName).locatorTypeCloseButtonAuth,
                locator = AuthScreenLocators(paramPlatformName).closeButtonAuth.id
            )
            println("экран авторизации закрыт без авторизации")

            TimeUnit.SECONDS.sleep(3)

            //кнопка разрешения геопозиции
            TestMethods().clickToElement(
                locatorType = CatalogScreenLocators(paramPlatformName).locatorTypePermissionAllow,
                locator = CatalogScreenLocators(paramPlatformName).permissionAllow.id
            )
            println("определение геопозиции разрешено")

            TimeUnit.SECONDS.sleep(3)

            //кнопка подсказки города
            TestMethods().clickToElement(
                locatorType = CatalogScreenLocators(paramPlatformName).locatorTypeChooseCityFromHelper,
                locator = CatalogScreenLocators(paramPlatformName).chooseCityFromHelper.id
            )
            println("выбран город из подсказки")

            TimeUnit.SECONDS.sleep(4)

        } else {
            TestMethods().clickToElement(
                locatorType = BottomBarLocators(paramPlatformName).locatorTypeDashboard,
                locator = BottomBarLocators(paramPlatformName).dashboard.id
            )
        }
    }

    fun initDriver(
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
        println("driver init success")

    }

}