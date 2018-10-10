package com.yshmgrt.school.model

import com.yshmgrt.school.util.P2D
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.canvas.GraphicsContext
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

open class Shape() {
    val srcProperty = SimpleObjectProperty<List<P2D>>(listOf())
    var src by srcProperty

    val nameProperty = SimpleStringProperty("")
    var name by nameProperty


    constructor(src : List<P2D>, name : String = "") : this(){
        this.name = name
        this.src = src
    }
    
    open fun transform(f : (P2D) -> P2D) = Shape(src.mapEach { f(this) }, name)

    open fun draw(parent : GraphicsContext) = with(parent) {
        fill = c(0.0, 0.0, 0.0)
        for (i in 1 until src.size)
            strokeLine(src[i].x, src[i].y, src[i - 1].x, src[i - 1].y)
        for (i in 0 until src.size)
            fillOval(src[i].x - 3, src[i].y - 3, 6.0, 6.0)
    }
}