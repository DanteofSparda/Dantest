package locators

import constructor_classes.LocatorsConstructor
import java.lang.RuntimeException

class EnterScreenLocators(platformType: String) {
    val buttonSignIn: LocatorsConstructor
    val locatorTypeButtonSignIn: LocatorType
    init {
        when(platformType){
            "Android" -> {
                buttonSignIn = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/buttonSignIn"
                )
                locatorTypeButtonSignIn = LocatorType.ID
            }
            "iOS" -> {
                buttonSignIn = LocatorsConstructor(
                    id = "buttonSignIn"
                )
                locatorTypeButtonSignIn = LocatorType.ACCESSIBILITY_ID
            }
            else -> {
                throw RuntimeException("Locator not found")
            }
        }
    }

}