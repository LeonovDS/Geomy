package com.yshmgrt.school.model

import com.yshmgrt.school.util.P2D
import com.yshmgrt.school.util.onFocusChanged
import com.yshmgrt.school.util.onTextChange
import com.yshmgrt.school.util.updateCanvas
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.canvas.GraphicsContext
import tornadofx.*
import javax.json.JsonObject
import kotlin.math.abs

class Circle() : IShape {
    override val type = "circle"

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("type", type)
            add("name", name)
            add("x", x)
            add("y", y)
            add("r", r)
        }
    }

    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name") ?: ""
            x = double("x") ?: 0.0
            y = double("y") ?: 0.0
            r = double("yr") ?: 0.0
        }
    }

    override fun xMin() = x - r
    override fun xMax() = x + r
    override fun yMin() = y - r
    override fun yMax() = y + r

    override fun getForm() = object : Fragment() {
        override val root = form {
            fieldset("Circle") {
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
            fieldset {
                field("r: ") {
                    textfield {
                        bind(rProperty)
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
    var rProperty = SimpleDoubleProperty(0.0)

    var x : Double by xProperty
    var y : Double by yProperty
    var r : Double by rProperty

    override val nameProperty = SimpleStringProperty("")
    override var name : String by nameProperty

    constructor(x : Double, y : Double, r : Double, name : String = "") : this(){
        this.name = name
        this.x = x
        this.y = y
        this.r = r
    }

    override fun transform(f : (P2D) -> P2D) : Circle {
        val x = f(P2D(this.x, this.y)).x
        val y = f(P2D(this.x, this.y)).y
        val x1 = f(P2D(this.x - r, this.y)).x
        return Circle(x, y, abs(x - x1), name)
    }

    override fun draw(parent : GraphicsContext) = with(parent) {
        fill = c(0.0, 0.0, 0.0)
        fillOval(x - 3, y - 3, 6.0, 6.0)
        strokeOval(x - r / 2, y - r / 2, r, r)
    }
}