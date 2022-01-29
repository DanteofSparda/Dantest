package locators

import constructor_classes.LocatorsConstructor

class SplashScreenLocators(platformType: String) {
    val exitButtonOnSplash: LocatorsConstructor
    val onbording: LocatorsConstructor
    val locatorTypeExitButtonOnSplash: LocatorType
    val locatorTypeOnbording: LocatorType
    init{
        when(platformType){
            "Android" ->{
                exitButtonOnSplash = LocatorsConstructor(
                    id = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton",
                )
                locatorTypeExitButtonOnSplash = LocatorType.XPATH

                onbording = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/imageViewGoodsSecond"
                )
                locatorTypeOnbording = LocatorType.ID
            }
            "iOS" -> {
                exitButtonOnSplash = LocatorsConstructor(
                    id = "closeButton"
                )
                locatorTypeExitButtonOnSplash = LocatorType.ACCESSIBILITY_ID

                onbording = LocatorsConstructor(
                    id = ""// TODO: 29.01.2022  
                )
                locatorTypeOnbording = LocatorType.ACCESSIBILITY_ID
            }
            else -> throw RuntimeException("Locator not found")
        }
    }

}