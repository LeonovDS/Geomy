package com.yshmgrt.school.model

import tornadofx.*

class ShapeModel : ItemViewModel<IShape>() {
    var name = bind{item?.nameProperty}
    //var src = bind{item?.srcProperty}
}