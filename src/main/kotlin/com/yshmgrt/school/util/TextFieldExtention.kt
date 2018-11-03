package com.yshmgrt.school.util

import javafx.scene.control.TextField
import tornadofx.*

fun TextField.onTextChange(f : () -> Unit) {
    setOnKeyPressed { _ ->
        f()
    }
}

fun TextField.onFocusChanged(f : () -> Unit) {
    focusedProperty().addListener{ _ ->
        f()
    }
}