package com.yshmgrt.school.view

import com.yshmgrt.school.controller.GeoController
import com.yshmgrt.school.model.Shape
import com.yshmgrt.school.model.ShapeModel
import com.yshmgrt.school.util.P2D
import javafx.scene.control.ListView
import javafx.scene.control.TreeItem
import tornadofx.*


class MainView : View("Hello TornadoFX") {

    val controller : GeoController by inject()
    val model = ShapeModel()
    var canvas : GeometryCanvas by singleAssign()
    var list : ListView<Shape> by singleAssign()

    override val root = vbox {
        menubar {
            menu("Foo") {
                item("Bar")
            }
        }
        hbox {
            vbox {
                list = listview(controller.shapes) {
                    cellFormat { shape ->
                        graphic = vbox {
                            label(shape.nameProperty)
                            button("Delete").action {
                                controller.shapes.remove(index, index + 1)
                                update()
                            }
                        }
                    }
                    onUserSelect {
                        update()
                    }
                    bindSelected(model)
                }
                button("Add").action {
                    controller.shapes.add(Shape(observableList(P2D())))
                    update()
                }
            }
            form {
                fieldset("Shape") {
                    field("Name") {textfield(model.name)}
                }
                listview(model.src) {
                    cellFormat {p ->
                        graphic = fieldset("Point") {
                            field("x: ") {textfield().bind(p.xProperty)}
                            field("y: ") {textfield().bind(p.yProperty)}
                            hbox {
                                button("Add").action {
                                    model.src.value.add(P2D())
                                    update()
                                }
                                button("Delete").action {
                                    model.src.value.remove(index, index + 1)
                                    update()
                                }
                            }
                        }
                    }
                }
                fieldset {
                    hbox {
                        button("Save").action {
                            model.commit()
                            update()
                        }
                        button("Cancel").action {
                            model.rollback()
                        }
                    }
                }
            }
            vbox {
                canvas = GeometryCanvas(controller)
                add(canvas)
                update()
            }
        }
        update()
    }

    private fun update() = canvas.apply{
        updateBounds()
        drawShapes()
        list.refresh()
    }
}