package com.yshmgrt.school.controller

import com.yshmgrt.school.model.Shape
import com.yshmgrt.school.util.P2D
import tornadofx.*

/**
 * Main controller of the project
 * @property shapes List of all shapes.
 * */
class GeoController : Controller() {
    var shapes = observableList(
            Shape(observableList(P2D(0.0, 0.0)), "A"),
            Shape(observableList(P2D(1.1, 2.2), P2D(1.3, 2.4), P2D(3.0, 0.0)), "AB")
    ).observable().onChange {
        println("ch")
    }
}