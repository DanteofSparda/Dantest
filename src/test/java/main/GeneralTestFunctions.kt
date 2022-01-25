package main

import constructor_classes.locatorsTypes
import locators.AuthScreenLocators
import locators.BottomBarLocators
import locators.EnterScreenLocators
import locators.ProfileScreenLocators
import org.testng.AssertJUnit
import java.util.concurrent.TimeUnit

class GeneralTestFunctions{

    //авторизация пользователя
    fun authorization(phoneNumber: String, smsNumber: String){
        TestMethods().clickToElement(locatorType = locatorsTypes.Id,
            locator = BottomBarLocators().profileButton.androidId
        )
        println("переход в личный кабинет")
        TimeUnit.SECONDS.sleep(3)

        //click on button signIn
        TestMethods().clickToElement(locatorType = locatorsTypes.Id,
            locator = EnterScreenLocators().buttonSignIn.androidId)

        //ввод в поле номера телефона
        TestMethods().inputTextInField(locatorType = locatorsTypes.Id,
            locator = AuthScreenLocators().editTextPhone.androidId,
            inputText = phoneNumber
        )
        println("телефон введен")

        //кнопка "получить смс"
        TestMethods().clickToElement(locatorType = locatorsTypes.Id,
            locator = AuthScreenLocators().buttonGetCode.androidId
        )
        println("кнопка на получение смс нажата")

        //ввод в поле sms
        TestMethods().inputTextInField(locatorType = locatorsTypes.Id,
            locator = AuthScreenLocators().pinCodeEditText.androidId,
            inputText = smsNumber
        )
        println("смс введено")

        TimeUnit.SECONDS.sleep(10)
    }

    //проверка на авторизованность (если true, то неавторизован
    fun authUserDetect(): Boolean {
        //кнопка личный кабинет
        TestMethods().clickToElement(locatorType = locatorsTypes.Id,
            locator = BottomBarLocators().profileButton.androidId
        )

        TimeUnit.SECONDS.sleep(3)

        //проверка на доступность кнопки "войти"
        return TestMethods().availableElement(locatorType = locatorsTypes.Id,
        locator = EnterScreenLocators().buttonSignIn.androidId)

    }

    //разлогин пользователя
    fun logout(){
        //кнопка личный кабинет
        TestMethods().clickToElement(locatorType = locatorsTypes.Id,
            locator = BottomBarLocators().profileButton.androidId
        )
        println("переход в личный кабинет")

        TimeUnit.SECONDS.sleep(2)

        //нажимаем на карандашик
        TestMethods().clickToElement(
            locatorType = locatorsTypes.Id,
            locator = ProfileScreenLocators().buttonEditProfile.androidId
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
            locatorType = locatorsTypes.Id,
            locator = ProfileScreenLocators().buttonLogout.androidId
        )
        println("кнопка разлогина нажата")

        TimeUnit.SECONDS.sleep(3)

        AssertJUnit.assertTrue(
            "Неуспешный разлогин", TestMethods().availableElement(
                locatorType = locatorsTypes.Id,
                locator = EnterScreenLocators().buttonSignIn.androidId
            )
        )
        println("Успешный разлогин")
    }

}