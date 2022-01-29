package locators

import constructor_classes.LocatorsConstructor

class ProfileScreenLocators(platformType: String) {
    val buttonEditProfile: LocatorsConstructor
    val buttonLogout: LocatorsConstructor
    val editTextLastName: LocatorsConstructor
    val editTextFirstName: LocatorsConstructor
    val buttonSave: LocatorsConstructor
    val emptyNameError: LocatorsConstructor

    val locatorTypeButtonEditProfile: LocatorType
    val locatorTypeButtonLogout: LocatorType
    val locatorTypeEditTextLastName: LocatorType
    val locatorTypeEditTextFirstName: LocatorType
    val locatorTypeButtonSave: LocatorType
    val locatorTypeEmptyNameError: LocatorType

    init {
        when(platformType){
            "Android" -> {
                buttonEditProfile = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/buttonEditProfile"
                )
                locatorTypeButtonEditProfile = LocatorType.ID

                buttonLogout = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/buttonLogout"
                )
                locatorTypeButtonLogout = LocatorType.ID

                editTextLastName = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/editTextLastName"
                )
                locatorTypeEditTextLastName = LocatorType.ID

                editTextFirstName = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/editTextFirstName"
                )
                locatorTypeEditTextFirstName = LocatorType.ID

                buttonSave = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/buttonSave"
                )
                locatorTypeButtonSave = LocatorType.ID

                emptyNameError = LocatorsConstructor(
                    id = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ViewFlipper/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView"
                )
                locatorTypeEmptyNameError = LocatorType.XPATH
            }
            "iOS" -> {
                buttonEditProfile = LocatorsConstructor(
                    id = "edit"
                )
                locatorTypeButtonEditProfile = LocatorType.ACCESSIBILITY_ID

                buttonLogout = LocatorsConstructor(
                    id = "buttonLogout"
                )
                locatorTypeButtonLogout = LocatorType.ACCESSIBILITY_ID

                editTextLastName = LocatorsConstructor(
                    id = ""// TODO: 29.01.2022
                )
                locatorTypeEditTextLastName = LocatorType.ACCESSIBILITY_ID

                editTextFirstName = LocatorsConstructor(
                    id = ""// TODO: 29.01.2022
                )
                locatorTypeEditTextFirstName = LocatorType.ID

                buttonSave = LocatorsConstructor(
                    id = "buttonSave"
                )
                locatorTypeButtonSave = LocatorType.ACCESSIBILITY_ID

                emptyNameError = LocatorsConstructor(
                    id = ""// TODO: 29.01.2022
                )
                locatorTypeEmptyNameError = LocatorType.XPATH
            }
            else -> throw RuntimeException("locator not found")
        }
    }


}