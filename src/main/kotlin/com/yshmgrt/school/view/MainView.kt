package com.yshmgrt.school.view


import com.yshmgrt.school.app.Styles
import com.yshmgrt.school.controller.GeoController
import tornadofx.*


class MainView : View("Hello TornadoFX") {

    val controller : GeoController by inject()

    override val root = hbox {
        label(title) {
            addClass(Styles.heading)
        }
    }
}