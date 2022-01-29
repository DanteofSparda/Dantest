package locators

enum class LocatorType(val code: String) {
    ID("id"),
    XPATH("xpath"),
    ACCESSIBILITY_ID("accessibilityId"),
    IOS_CLASS_CHAIN("classChain");
}