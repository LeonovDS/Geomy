package com.yshmgrt.school.model

import com.yshmgrt.school.util.P2D
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.canvas.GraphicsContext
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

open class Shape() {
    val srcProperty = SimpleObjectProperty(FXCollections.observableArrayList<P2D>())
    var src by srcProperty


    val nameProperty = SimpleObjectProperty<String>("")
    var name by nameProperty


    constructor(src : ObservableList<P2D>, name : String = "") : this(){
        this.name = name
        this.src = src
    }
    
    open fun transform(f : (P2D) -> P2D) = Shape(src.map(f).observable(), name)

    open fun draw(parent : GraphicsContext) = with(parent) {
        fill = c(0.0, 0.0, 0.0)
        for (i in 1 until src.size)
            strokeLine(src[i].x, src[i].y, src[i - 1].x, src[i - 1].y)
        for (i in 0 until src.size)
            fillOval(src[i].x - 3, src[i].y - 3, 6.0, 6.0)
    }

    open fun createForm() : Form {
        val model = ShapeModel()
        model.item = this
        return Form().apply {
            fieldset("Name") {
                field("Name") {
                    textfield(name) {
                        bind(model.name)
                    }
                }
            }
            for (i in 0 until src.size) {
                fieldset("Point") {
                    field("x: ") {
                        textfield {
                            bind(model.item.src[i].x.toProperty())
                        }
                    }
                    field("y: ") {
                        textfield {
                            bind(model.item.src[i].y.toProperty())
                        }
                    }
                }
            }
            button("Save").action {
                model.commit()
            }
        }
    }
}