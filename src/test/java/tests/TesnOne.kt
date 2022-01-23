package tests

import constructor_classes.locatorsTypes
import locators.*
import main.TestMethods
import org.openqa.selenium.WebDriverException
import org.testng.AssertJUnit
import org.testng.annotations.Test
import java.util.concurrent.TimeUnit

class TestOne: TestMethods() {

    @Test
    fun testOne(){
        TimeUnit.SECONDS.sleep(1)

        //нажатие на крестик
        try {
            clickToElement(locatorType = locatorsTypes.xpath,
            locator = SplashScreenLocators().exitButtonOnSplashScreen.androidXpath
            )

        } catch (e: WebDriverException){
            println("элемент не найден, тест продолжается")
        }


        //ввод в поле номера телефона
        inputTextInField(locatorType = locatorsTypes.Id,
                locator = AuthScreenLocators().editTextPhone.androidId,
                inputText = "9999999901"
        )
        println("телефон введен")


        //кнопка "получить смс"
        clickToElement(locatorType = locatorsTypes.Id,
                locator = AuthScreenLocators().buttonGetCode.androidId
        )
        println("кнопка на получение смс нажата")


        //ввод в поле sms
        inputTextInField(locatorType = locatorsTypes.Id,
                locator = AuthScreenLocators().pinCodeEditText.androidId,
                inputText = "1111"
        )
        println("смс введено")


        TimeUnit.SECONDS.sleep(10)

        //кнопка разрешения геопозиции
        clickToElement(locatorType = locatorsTypes.Id,
                locator = CatalogScreenLocators().permissionAllow.androidId
        )
        println("определение геопозиции разрешено")

        TimeUnit.SECONDS.sleep(5)

        //кнопка подсказки города
        clickToElement(locatorType = locatorsTypes.Id,
                locator = CatalogScreenLocators().chooseCityFromHelper.androidId
        )
        println("выбран город из подсказки")

        TimeUnit.SECONDS.sleep(5)

        //кнопка личный кабинет
        clickToElement(locatorType = locatorsTypes.Id,
                locator = BottomBarLocators().profileButton.androidId
        )
        println("переход в личный кабинет")


        TimeUnit.SECONDS.sleep(3)

        //нажимаем на карандашик
        clickToElement(locatorType = locatorsTypes.Id,
                locator = ProfileScreenLocators().buttonEditProfile.androidId
        )
        println("переход на экран редактирования данных пользователя")

        TimeUnit.SECONDS.sleep(3)


        //очищаем поле имени
        clearField(locatorType = locatorsTypes.Id,
        locator = ProfileScreenLocators().editTextFirstName.androidId
        )
        println("поле имени очистили")

        //прокручиваем экран к низу
        swipeOnScreen(
                startCordX = 500,
                startCordY = 1300,
                moveCordX = 500,
                moveCordY = 750
        )

        //click save
        clickToElement(locatorType = locatorsTypes.Id,
        locator = ProfileScreenLocators().buttonSave.androidId)

        //scroll top
        swipeOnScreen(
                startCordX = 500,
                startCordY = 750,
                moveCordX = 500,
                moveCordY = 1300
        )

        //compare error text
        try{
            checkTextInElement(locatorType = locatorsTypes.xpath,
                    locator = ProfileScreenLocators().emptyNameError.androidXpath,
                    textToCompare = "Поле не может быть пустым!"
            )
            println("текст ошибки о пустом поле верный")
        } catch (e: java.lang.AssertionError){
            println("текст ошибки о пустом поле неверный, тест продолжается")
        }


        //scroll bottom
        swipeOnScreen(
                startCordX = 500,
                startCordY = 1300,
                moveCordX = 500,
                moveCordY = 750
        )

        //нажимаем на кнопку разлогина
        clickToElement(locatorType = locatorsTypes.Id,
                locator = ProfileScreenLocators().buttonLogout.androidId
        )
        println("кнопка разлогина нажата")

        TimeUnit.SECONDS.sleep(4)

        AssertJUnit.assertTrue("Неуспешный разлогин",checkAvailableElement(locatorType = locatorsTypes.Id,
                locator = EnterScreenLocators().buttonSignIn.androidId
                ))
        println("Успешный разлогин")

        TimeUnit.SECONDS.sleep(2)


    }


}