package com.yshmgrt.school.view

import com.yshmgrt.school.controller.GeoController
import com.yshmgrt.school.model.Shape
import javafx.scene.control.TreeItem
import tornadofx.*


class MainView : View("Hello TornadoFX") {

    val controller : GeoController by inject()
    val model = ItemViewModel(Shape())

    override val root = vbox {
        menubar {
            menu("Foo") {
                item("Bar")
            }
        }
        hbox {
            listview(controller.shapes) {
                cellFormat { shape ->
                    text = shape.name
                }
                model.rebindOnChange(this) { it ->
                    this.item = it ?: Shape()
                    print("Rebind ${model.item.name}")
                }
            }
            label(stringBinding(model.item.name){this})
            vbox {
                //Canvas
            }
            vbox {
                form {
                }
                /*hbox {
                    button ("Save").action {
                        curr.save()
                    }
                }*/
            }
        }
    }
}