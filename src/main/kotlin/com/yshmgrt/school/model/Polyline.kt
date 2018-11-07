package com.yshmgrt.school.model

import com.yshmgrt.school.util.P2D
import com.yshmgrt.school.util.*
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.canvas.GraphicsContext
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue
import javax.json.JsonObject


class Polyline() : IShape {

    override val type: String
        get() = "polyline"

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("type", type)
            add("name", name)
            add("src", src)
        }
    }

    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name") ?: ""
            src.setAll(getJsonArray("src").toModel())
        }
    }

    override fun xMin() = src.map {it -> it.x}.min() ?: 0.0
    override fun xMax() = src.map {it -> it.x}.max() ?: 0.0
    override fun yMin() = src.map {it -> it.y}.min() ?: 0.0
    override fun yMax() = src.map {it -> it.y}.max() ?: 0.0

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
            fieldset{
                button("Add").action {
                    src.add(0, P2D())
                }
            }
            listview(src) {
                cellFormat { p ->
                    graphic = fieldset("Point") {
                        field("x: ") {
                            textfield().apply {
                                bind(p.xProperty)
                                onTextChange { updateCanvas() }
                                onFocusChanged { updateCanvas() }
                            }
                        }
                        field("y: ") {
                            textfield().apply {
                                bind(p.yProperty)
                                onTextChange { updateCanvas() }
                                onFocusChanged { updateCanvas() }
                            }
                        }
                        hbox {
                            button("Add").action {
                                src.add(index + 1, P2D())
                                updateLists()
                            }
                            button("Delete").action {
                                src.remove(index, index + 1)
                                updateLists()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun create() = Polyline()

    val srcProperty = SimpleObjectProperty(FXCollections.observableArrayList<P2D>())
    var src: ObservableList<P2D> by srcProperty


    override val nameProperty = SimpleStringProperty("")
    override var name : String by nameProperty


    constructor(src : ObservableList<P2D>, name : String = "") : this(){
        this.name = name
        this.src = src.map { it -> it }.observable()
    }
    
    override fun transform(f : (P2D) -> P2D) = Polyline(src.map{it as P2D}.map(f).observable(), name)

    override fun draw(parent : GraphicsContext) = with(parent) {
        fill = c(0.0, 0.0, 0.0)
        for (i in 1 until src.size)
            strokeLine(src[i].x, src[i].y, src[i - 1].x, src[i - 1].y)
        for (i in 0 until src.size)
            fillOval(src[i].x - 3, src[i].y - 3, 6.0, 6.0)
    }
}
