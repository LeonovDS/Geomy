package com.yshmgrt.school.controller

import com.yshmgrt.school.model.IShape
import com.yshmgrt.school.model.Point
import com.yshmgrt.school.model.Polyline
import com.yshmgrt.school.model.Segment
import com.yshmgrt.school.util.P2D
import javafx.collections.ObservableList
import tornadofx.*

/**
 * Main controller of the project
 * @property shapes List of all shapes.
 * */
class GeoController : Controller() {
    var shapes = observableList(
            Polyline(observableList(P2D(0.0, 0.0)), "A"),
            Polyline(observableList(P2D(1.1, 2.2), P2D(1.3, 2.4), P2D(3.0, 0.0)), "AB"),
            Point(P2D(0.1, 0.2), "C"),
            Segment(observableList(P2D(1.1, 2.2), P2D(1.3, 2.4)), "AQ")
    )
}