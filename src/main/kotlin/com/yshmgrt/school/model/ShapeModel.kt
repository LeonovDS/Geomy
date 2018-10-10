package com.yshmgrt.school.model

import tornadofx.*

class ShapeModel : ItemViewModel<Shape>() {
    var name = bind(Shape::nameProperty)
    var src = bind(Shape::srcProperty)
}