package locators

import constructor_classes.LocatorsConstructor

class CatalogScreenLocators(platformType: String) {
    val permissionAllow: LocatorsConstructor
    val chooseCityFromHelper: LocatorsConstructor
    val locatorTypePermissionAllow: LocatorType
    val locatorTypeChooseCityFromHelper: LocatorType
    init {
        when(platformType){
            "Android" -> {
                permissionAllow = LocatorsConstructor(
                    id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button"
                )
                locatorTypePermissionAllow = LocatorType.ID

                chooseCityFromHelper = LocatorsConstructor(
                    id = "android:id/button1"
                )
                locatorTypeChooseCityFromHelper = LocatorType.ID
            }
            "iOS" -> {
                permissionAllow = LocatorsConstructor(
                    id = "Allow While Using App"
                )
                locatorTypePermissionAllow = LocatorType.ACCESSIBILITY_ID

                chooseCityFromHelper = LocatorsConstructor(
                    id = "button1"
                )
                locatorTypeChooseCityFromHelper = LocatorType.ACCESSIBILITY_ID
            }
            else -> {
                throw RuntimeException("Locator not found")
            }
        }
    }

}