package constructor_classes

import io.appium.java_client.MobileBy

class LocatorsConstructor constructor(
        val androidAccessibilityId: String = "",
        val androidId: String = "",
        val androidXpath: String = "",
        val iosId: String = "",
        val iosXpath: String = ""
)

val example = LocatorsConstructor(
        androidAccessibilityId = "",
        androidId = "",
        androidXpath = ""
)