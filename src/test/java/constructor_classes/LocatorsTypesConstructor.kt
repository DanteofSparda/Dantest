package constructor_classes

data class LocatorsTypesConstructor(
        val id: String = "id",
        val xpath: String = "xpath",
        val accessibilityId: String = "accessibilityId",
        val classChain: String = "classChain"

)

val locatorsTypes = LocatorsTypesConstructor()