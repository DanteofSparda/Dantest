package locators

import constructor_classes.LocatorsConstructor
import constructor_classes.LocatorsTypesConstructor
import constructor_classes.locatorsTypes

class AuthScreenLocators(platformType: String) {
    val editTextPhone: LocatorsConstructor
    val buttonGetCode: LocatorsConstructor
    val pinCodeEditText: LocatorsConstructor
    val closeButtonAuth: LocatorsConstructor
    val locatorTypeEditTextPhone: LocatorType
    val locatorTypeButtonGetCode: LocatorType
    val locatorTypePinCodeEditText: LocatorType
    val locatorTypeCloseButtonAuth: LocatorType

    init {
        when(platformType){
            "Android" -> {
                editTextPhone = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/editTextPhone"
                )
                locatorTypeEditTextPhone = LocatorType.ID

                buttonGetCode = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/buttonGetCode"
                )
                locatorTypeButtonGetCode = LocatorType.ID

                pinCodeEditText = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/pinCodeEditText"
                )
                locatorTypePinCodeEditText = LocatorType.ID

                closeButtonAuth = LocatorsConstructor(
                    id = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageButton"
                )
                locatorTypeCloseButtonAuth = LocatorType.XPATH
            }
            "iOS" -> {
                editTextPhone = LocatorsConstructor(
                    id = "//XCUIElementTypeOther[@name=\"editTextPhone\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField"// TODO: 29.01.2022
                )
                locatorTypeEditTextPhone = LocatorType.XPATH

                buttonGetCode = LocatorsConstructor(
                    id = "buttonGetCode"
                )
                locatorTypeButtonGetCode = LocatorType.ACCESSIBILITY_ID

                pinCodeEditText = LocatorsConstructor(
                    id = "pinCodeEditText"
                )
                locatorTypePinCodeEditText = LocatorType.ACCESSIBILITY_ID

                closeButtonAuth = LocatorsConstructor(
                    id = ""// TODO: 29.01.2022
                )
                locatorTypeCloseButtonAuth = LocatorType.XPATH
            }
            else -> throw RuntimeException("Locator not found")
        }
    }
}