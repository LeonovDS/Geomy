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
import java.lang.Double.max
import java.lang.Double.min

class Segment() : IShape {
    override fun xMin() = min(x1, x2)
    override fun xMax() = max(x1, x2)
    override fun yMin() = min(y1, y2)
    override fun yMax() = max(y1, y2)

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
            fieldset("Start") {
                field("x: ") {
                    textfield {
                        bind(x1Property)
                        onTextChange { updateCanvas() }
                        onFocusChanged { updateCanvas() }
                    }
                }
                field("y: ") {
                    textfield {
                        bind(y1Property)
                        onTextChange { updateCanvas() }
                        onFocusChanged { updateCanvas() }
                    }
                }
            }
            fieldset("Finish") {
                field("x: ") {
                    textfield {
                        bind(x2Property)
                        onTextChange { updateCanvas() }
                        onFocusChanged { updateCanvas() }
                    }
                }
                field("y: ") {
                    textfield {
                        bind(y2Property)
                        onTextChange { updateCanvas() }
                        onFocusChanged { updateCanvas() }
                    }
                }
            }
        }
    }

    override fun create() = Segment()

    private var x1Property = SimpleDoubleProperty(0.0)
    private var y1Property = SimpleDoubleProperty(0.0)
    private var x2Property = SimpleDoubleProperty(0.0)
    private var y2Property = SimpleDoubleProperty(0.0)

    var x1 : Double by x1Property
    var y1 : Double by y1Property
    var x2 : Double by x2Property
    var y2 : Double by y2Property


    override val nameProperty = SimpleStringProperty("")
    override var name : String by nameProperty


    constructor(src : ObservableList<P2D>, name : String = "") : this(){
        this.name = name
        if (src.size > 0) {
            x1 = src[0].x
            y1 = src[0].y
        }
        if (src.size > 1) {
            x2 = src[1].x
            y2 = src[1].y
        }
    }

    override fun transform(f : (P2D) -> P2D) = Segment(observableList(f(P2D(x1, y1)), f(P2D(x2, y2))), name)

    override fun draw(parent : GraphicsContext) = with(parent) {
        fill = c(0.0, 0.0, 0.0)
        fillOval(x1 - 3, y1 - 3, 6.0, 6.0)
        fillOval(x2 - 3, y2 - 3, 6.0, 6.0)
        strokeLine(x1, y1, x2, y2)
    }
}
