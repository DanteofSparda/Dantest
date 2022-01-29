package main

import constructor_classes.locatorsTypes
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import locators.LocatorType
import org.testng.AssertJUnit
import utils.PlatformTouchAction
import java.lang.RuntimeException
import java.time.Duration
import java.util.concurrent.TimeUnit

open class TestMethods {

    fun clickToElement(locatorType:LocatorType,locator:String){
        lateinit var element: MobileElement
        when (locatorType){
            LocatorType.ID -> element=driver.findElement(MobileBy.id(locator))
            LocatorType.XPATH -> element=driver.findElement(MobileBy.xpath(locator))
            LocatorType.ACCESSIBILITY_ID -> element=driver.findElement(MobileBy.AccessibilityId(locator))
            LocatorType.IOS_CLASS_CHAIN -> element=driver.findElement(MobileBy.iOSClassChain(locator))
        }
        element.click()
        TimeUnit.SECONDS.sleep(1)
    }

    fun inputTextInField(locatorType:LocatorType,locator:String,inputText:String){
        lateinit var element: MobileElement
        when (locatorType){
            LocatorType.ID -> element=driver.findElement(MobileBy.id(locator))
            LocatorType.XPATH -> element=driver.findElement(MobileBy.xpath(locator))
            LocatorType.ACCESSIBILITY_ID -> element=driver.findElement(MobileBy.AccessibilityId(locator))
            LocatorType.IOS_CLASS_CHAIN -> element=driver.findElement(MobileBy.iOSClassChain(locator))
        }
        element.sendKeys(inputText)
        TimeUnit.SECONDS.sleep(1)

    }

    fun clearField(locatorType:LocatorType,locator:String){
        lateinit var element: MobileElement
        when (locatorType){
            LocatorType.ID -> element=driver.findElement(MobileBy.id(locator))
            LocatorType.XPATH -> element=driver.findElement(MobileBy.xpath(locator))
            LocatorType.ACCESSIBILITY_ID -> element=driver.findElement(MobileBy.AccessibilityId(locator))
            LocatorType.IOS_CLASS_CHAIN -> element=driver.findElement(MobileBy.iOSClassChain(locator))
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
    fun checkAvailableElement (locatorType: LocatorType,locator: String): Boolean {
        return when(locatorType) {
            LocatorType.ID -> driver.findElement(MobileBy.id(locator)).isEnabled;
            LocatorType.XPATH -> driver.findElement(MobileBy.xpath(locator)).isEnabled;
            LocatorType.ACCESSIBILITY_ID -> driver.findElement(MobileBy.AccessibilityId(locator)).isEnabled;
            LocatorType.IOS_CLASS_CHAIN -> driver.findElement(MobileBy.iOSClassChain(locator)).isEnabled;
            else -> throw RuntimeException("некорректный тип локатора")
        }
    }

    fun checkTextInElement(locatorType: LocatorType,locator: String,textToCompare:String = ""){
        lateinit var element: String
        when(locatorType){
            LocatorType.ID-> element = driver.findElement(MobileBy.id(locator)).text
            LocatorType.XPATH -> element = driver.findElement(MobileBy.xpath(locator)).text
            LocatorType.ACCESSIBILITY_ID -> element=driver.findElement(MobileBy.AccessibilityId(locator)).text
            LocatorType.IOS_CLASS_CHAIN -> element=driver.findElement(MobileBy.iOSClassChain(locator)).text
        }
        AssertJUnit.assertEquals("Строки не равны",textToCompare,element)
    }

    fun availableElement(locatorType: LocatorType,locator: String): Boolean {
        try {
            return when(locatorType){
                LocatorType.ID -> driver.findElement(MobileBy.id(locator)).isEnabled
                LocatorType.XPATH -> driver.findElement(MobileBy.xpath(locator)).isEnabled
                LocatorType.ACCESSIBILITY_ID -> driver.findElement(MobileBy.AccessibilityId(locator)).isEnabled
                LocatorType.IOS_CLASS_CHAIN -> driver.findElement(MobileBy.iOSClassChain(locator)).isEnabled
            }
        } catch(e: org.openqa.selenium.NoSuchElementException){
            return false
        }
    }
}