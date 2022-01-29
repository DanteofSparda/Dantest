package locators

import constructor_classes.LocatorsConstructor
import java.lang.RuntimeException

class BottomBarLocators(platformType: String) {
    val locatorTypeProfileButton: LocatorType
    val locatorTypeDashboard: LocatorType
    val profileButton: LocatorsConstructor
    val dashboard: LocatorsConstructor
    init {
        when(platformType){
            "Android" -> {
                profileButton = LocatorsConstructor(
                    id = "ru.sportmaster.app.handh.dev:id/profile_graph"
                )
                locatorTypeProfileButton = LocatorType.ID

                dashboard = LocatorsConstructor (
                    id = "ru.sportmaster.app.handh.dev:id/dashboard_graph"
                )
                locatorTypeDashboard = LocatorType.ID
            }
            "iOS" -> {
                profileButton = LocatorsConstructor(
                    id = "profile_graph"
                )
                locatorTypeProfileButton = LocatorType.ACCESSIBILITY_ID

                dashboard = LocatorsConstructor (
                    id = "" // TODO: 29.01.2022
                )
                locatorTypeDashboard = LocatorType.ACCESSIBILITY_ID
            }
            else -> {
                throw RuntimeException("Locator not found")
            }
        }
    }
}