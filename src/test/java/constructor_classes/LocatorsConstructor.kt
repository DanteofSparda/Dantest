package constructor_classes

class LocatorsConstructor constructor(
    val id: String = "",
    val xPath: String = "",
    val accessibilityId: String = ""
)

val example = LocatorsConstructor(
        accessibilityId = "",
        id = ""
)