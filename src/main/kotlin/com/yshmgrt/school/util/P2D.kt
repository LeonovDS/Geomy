package com.yshmgrt.school.util

import javafx.beans.property.SimpleDoubleProperty
import tornadofx.*
import javax.json.JsonObject

class P2D(a : Double = 0.0, b : Double = 0.0) : JsonModel {
    val xProperty : SimpleDoubleProperty = SimpleDoubleProperty(a)
    val yProperty : SimpleDoubleProperty = SimpleDoubleProperty(b)
    var x by xProperty
    var y by yProperty

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("x", x)
            add("y", y)
        }
    }

    override fun updateModel(json: JsonObject) {
        with(json) {
            x = double("x") ?: 0.0
            y = double("y") ?: 0.0
        }
    }
}

class P2DModel : ItemViewModel<P2D>() {
    var x = bind{item?.xProperty}
    var y = bind{item?.yProperty}
}