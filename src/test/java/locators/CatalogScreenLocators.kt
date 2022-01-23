package locators

import constructor_classes.LocatorsConstructor

class CatalogScreenLocators {
    val permissionAllow = LocatorsConstructor(
            androidId = "com.android.permissioncontroller:id/permission_allow_foreground_only_button"
    )

    val chooseCityFromHelper = LocatorsConstructor(
            androidId = "android:id/button1"
    )
}