package com.yshmgrt.school.model

import com.yshmgrt.school.util.P2D
import com.yshmgrt.school.util.*
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.canvas.GraphicsContext
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

class Point() : IShape {
    override fun xMin() = x
    override fun xMax() = x
    override fun yMin() = y
    override fun yMax() = y

    override fun getForm() = object : Fragment() {
        override val root = form {
            fieldset("Polyline") {
                field("Name") {
                    textfield().apply {
                        bind(nameProperty)
                        //onTextChange { updateLists() }
                    }
                }
            }
            fieldset {
                field("x: ") {
                    textfield {
                        bind(xProperty)
                        onTextChange { updateCanvas() }
                        onFocusChanged { updateCanvas() }
                    }
                }
                field("y: ") {
                    textfield {
                        bind(yProperty)
                        onTextChange { updateCanvas() }
                        onFocusChanged { updateCanvas() }
                    }
                }
            }
        }
    }

    override fun create() = Point()

    var xProperty = SimpleDoubleProperty(0.0)
    var yProperty = SimpleDoubleProperty(0.0)

    var x : Double by xProperty
    var y : Double by yProperty



    override val nameProperty = SimpleStringProperty("")
    override var name : String by nameProperty


    constructor(src : ObservableList<P2D>, name : String = "") : this(){
        this.name = name
        if (src.size > 0) {
            x = src[0].x
            y = src[0].y
        }
    }

    constructor(p : P2D, name : String = "") : this(){
        this.name = name
        x = p.x
        y = p.y
    }

    override fun transform(f : (P2D) -> P2D) = Point(f(P2D(x, y)), name)

    override fun draw(parent : GraphicsContext) = with(parent) {
        fill = c(0.0, 0.0, 0.0)
        fillOval(x - 3, y - 3, 6.0, 6.0)
    }
}
