package locators

import constructor_classes.LocatorsConstructor

class LocatorsIOS {

    val buttonSignIn = LocatorsConstructor(
            id = "buttonSignIn"

        //1) accessibility id: Ask App Not to Track
        //-ios class chain (docs) : **/XCUIElementTypeButton[`label == "Ask App Not to Track"`]

        //2) accessibility id : Don’t Allow
        //-ios class chain (docs)  : **/XCUIElementTypeButton[`label == "Don’t Allow"`]

    //accessibility id : "closeButton" // на сплеш экране кнопка закрыть



// -ios class chain : **/XCUIElementTypeTextField[`value == "Номер телефона"`]
// -ios predicate string : value == "Номер телефона"
// xpath : //XCUIElementTypeOther[@name="editTextPhone"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField

    // accessibility id : "buttonGetCode"

    //accessibility id : "pinCodeEditText" //поле ввода смс

    //accessibility id : "Allow While Using App"

    //accessibility id : "button1" //выбор города из подсказки

    //accessibility id : "profile_graph"

    //accessibility id : "edit" //кнопка редактирования данных пользователя

    //-ios class chain (docs) : "**/XCUIElementTypeTextField[`value == "Крекер"`]" //поле ввода имени
    //-ios predicate string : "value == "Крекер"


    //accessibility id : "buttonLogout"

    //accessibility id : "buttonSave"

    //accessibility id : buttonSignIn
    //-ios class chain (docs) : **/XCUIElementTypeButton[`label == "Войти"`]
    )
}