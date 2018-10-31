package com.yshmgrt.school.util

import javafx.beans.property.SimpleDoubleProperty
import tornadofx.*

class P2D(a : Double = 0.0, b : Double = 0.0) {
    val xProperty : SimpleDoubleProperty = SimpleDoubleProperty(a)
    val yProperty : SimpleDoubleProperty = SimpleDoubleProperty(b)
    var x by xProperty
    var y by yProperty
}

class P2DModel : ItemViewModel<P2D>() {
    var x = bind{item?.xProperty}
    var y = bind{item?.yProperty}
}