package locators

import constructor_classes.LocatorsConstructor

class AuthScreenLocators {
    val editTextPhone = LocatorsConstructor(
            androidId = "ru.sportmaster.app.handh.dev:id/editTextPhone"
    )

    val buttonGetCode = LocatorsConstructor(
            androidId = "ru.sportmaster.app.handh.dev:id/buttonGetCode"
    )

    val pinCodeEditText = LocatorsConstructor(
            androidId = "ru.sportmaster.app.handh.dev:id/pinCodeEditText"
    )

    val closeButtonAuth = LocatorsConstructor(
        androidXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageButton"
    )
}