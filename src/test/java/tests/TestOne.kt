package tests

import constructor_classes.locatorsTypes
import locators.*
import main.BaseClass
import main.GeneralTestFunctions
import main.TestMethods
import org.openqa.selenium.WebDriverException
import org.testng.AssertJUnit
import org.testng.annotations.Test
import java.util.concurrent.TimeUnit

class TestOne: BaseClass() {

    @Test
    fun testOne(){

        //если пользователь разлогинен, то авторизуем его
        if (generalTestFunctions.authUserDetect())
        {
            generalTestFunctions.authorization(phoneNumber="9999999901", smsNumber = "1111")

            /*тут нужно для начала на карандаш клацнуть
            //очищаем поле имени
            testMethods.clearField(
                locatorType = locatorsTypes.Id,
                locator = ProfileScreenLocators().editTextFirstName.androidId
            )
            println("поле имени очистили")

            //прокручиваем экран к низу
            testMethods.swipeOnScreen(
                startCordX = 500,
                startCordY = 1300,
                moveCordX = 500,
                moveCordY = 750
            )

            //click save
            testMethods.clickToElement(
                locatorType = locatorsTypes.Id,
                locator = ProfileScreenLocators().buttonSave.androidId
            )

            //scroll top
            testMethods.swipeOnScreen(
                startCordX = 500,
                startCordY = 750,
                moveCordX = 500,
                moveCordY = 1300
            )

            //compare error text
            try {
                testMethods.checkTextInElement(
                    locatorType = locatorsTypes.xpath,
                    locator = ProfileScreenLocators().emptyNameError.androidXpath,
                    textToCompare = "Поле не может быть пустым!"
                )
                println("текст ошибки о пустом поле верный")
            } catch (e: java.lang.AssertionError) {
                println("текст ошибки о пустом поле неверный, тест продолжается")
            }

            */
        } else {

            generalTestFunctions.logout()

            }

        TimeUnit.SECONDS.sleep(2)
        }

    }

