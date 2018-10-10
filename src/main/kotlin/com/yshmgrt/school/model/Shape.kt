package com.yshmgrt.school.model

import com.yshmgrt.school.util.P2D
import javafx.scene.canvas.GraphicsContext
import tornadofx.*

open class Shape() {
    var src = listOf<P2D>()
    var name = ""

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