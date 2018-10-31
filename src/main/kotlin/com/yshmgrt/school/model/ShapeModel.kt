package com.yshmgrt.school.model

import com.yshmgrt.school.util.P2DModel
import tornadofx.*

class ShapeModel : ItemViewModel<Shape>() {
    var name = bind{item?.nameProperty}
    var src = bind{item?.srcProperty}
}