package main

import constructor_classes.locatorsTypes
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.testng.AssertJUnit
import utils.PlatformTouchAction
import java.lang.RuntimeException
import java.time.Duration
import java.util.concurrent.TimeUnit

open class TestMethods {

    fun clickToElement(locatorType:String,locator:String){

        lateinit var element: MobileElement
        when (locatorType){
            locatorsTypes.Id -> element=driver.findElement(MobileBy.id(locator))
            locatorsTypes.xpath -> element=driver.findElement(MobileBy.xpath(locator))
        }

        element.click()
        TimeUnit.SECONDS.sleep(1)
    }

    fun inputTextInField(locatorType:String,locator:String,inputText:String){
        lateinit var element: MobileElement
        when (locatorType){
            locatorsTypes.Id -> element=driver.findElement(MobileBy.id(locator))
            locatorsTypes.xpath -> element=driver.findElement(MobileBy.xpath(locator))
        }

        element.sendKeys(inputText)
        TimeUnit.SECONDS.sleep(1)

    }

    fun clearField(locatorType:String,locator:String){
        lateinit var element: MobileElement
        when (locatorType){
            locatorsTypes.Id -> element=driver.findElement(MobileBy.id(locator))
            locatorsTypes.xpath -> element=driver.findElement(MobileBy.xpath(locator))
        }

        element.clear()//очищение поля
        TimeUnit.SECONDS.sleep(1)

    }

    fun  swipeOnScreen(
            startCordX: Int,
            startCordY: Int,
            moveCordX: Int,
            moveCordY: Int
    ){
        PlatformTouchAction(driver)
                .longPress(PointOption.point(startCordX, startCordY))
                .moveTo(PointOption.point(moveCordX, moveCordY))
                .release()
                .perform()
    }

    //Тап по координатам на экране
    fun tapByCoordinates(
            cordX: Int,
            cordY: Int,
    ){
        PlatformTouchAction(driver)
                .tap(PointOption.point(cordX, cordY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .perform()
    }

    // проверка доступности элемента на экране
    fun checkAvailableElement (locatorType: String,locator: String): Boolean {
        return when(locatorType) {
            locatorsTypes.Id -> driver.findElement(MobileBy.id(locator)).isEnabled;
            locatorsTypes.xpath -> driver.findElement(MobileBy.xpath(locator)).isEnabled;
            else -> throw RuntimeException("некорректный тип локатора")
        }
    }

    fun checkTextInElement(locatorType: String,locator: String,textToCompare:String = ""){
        lateinit var element: String
        when(locatorType){
            locatorsTypes.Id -> element = driver.findElement(MobileBy.id(locator)).text
            locatorsTypes.xpath -> element = driver.findElement(MobileBy.xpath(locator)).text
        }
        AssertJUnit.assertEquals("Строки не равны",textToCompare,element)
    }

    fun availableElement(locatorType: String,locator: String): Boolean {
        var element : Boolean = false
        try {
            when(locatorType){
                locatorsTypes.Id -> element = driver.findElement(MobileBy.id(locator)).isEnabled
                locatorsTypes.xpath -> element = driver.findElement(MobileBy.xpath(locator)).isEnabled
            }
        } catch(e: org.openqa.selenium.NoSuchElementException){
            element = false
        }
        return element
    }
}