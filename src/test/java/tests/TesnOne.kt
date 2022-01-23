package tests

import constructor_classes.locatorsTypes
import locators.AuthScreenLocators
import locators.BottomBarLocators
import locators.CatalogScreenLocators
import locators.SplashScreenLocators
import main.TestMethods
import org.openqa.selenium.WebDriverException
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

        TimeUnit.SECONDS.sleep(2)

        swipeOnScreen(
                startCordX = 500,
                startCordY = 1200,
                moveCordX = 500,
                moveCordY = 800
        )

        TimeUnit.SECONDS.sleep(5)
    }



}