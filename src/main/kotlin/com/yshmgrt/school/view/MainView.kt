package com.yshmgrt.school.view

import com.yshmgrt.school.controller.GeoController
import com.yshmgrt.school.model.IShape
import com.yshmgrt.school.model.Polyline
import com.yshmgrt.school.model.ShapeModel
import com.yshmgrt.school.util.*
import javafx.scene.control.ListView
import javafx.scene.layout.HBox
import tornadofx.*


class MainView : View("Hello TornadoFX") {

    private val controller : GeoController by inject()
    private val model = ShapeModel()
    var canvas : GeometryCanvas by singleAssign()
    var list : ListView<IShape> by singleAssign()
    var fragment : Fragment? = null
    lateinit var hb : HBox

    override val root = vbox {
        menubar {
            menu("Foo") {
                item("Bar")
            }
        }
        hb = hbox {
            vbox {
                list = listview(controller.shapes) {
                    cellFormat { shape ->
                        graphic = vbox {
                            label(shape.nameProperty)
                            button("Delete").action {
                                controller.shapes.remove(index, index + 1)
                                updateLists()
                            }
                            setOnMouseClicked { updateLists() }
                        }
                    }
                    bindSelected(model)
                }
                hbox {
                    vbox {
                        button("Add").action {
                            controller.shapes.add(Polyline(observableList(P2D())))
                            updateLists()
                        }
                        hbox {

                        }
                    }
                }
            }
            vbox {
                canvas = GeometryCanvas(controller)
                add(canvas)
            }
        }
        updateLists()
    }

    init {
        subscribe<UpdateCanvasEvent> {
            updateCanvas()
        }
        subscribe<UpdateListsEvent> {
            updateLists()
        }
    }

    private fun updateLists() = runLater {
        updateCanvas()
        list.refresh()
        fragment?.removeFromParent()
        fragment = model.item?.getForm() ?: EmptyFragment()
        hb.add(fragment!!)
    }

    private fun updateCanvas() = runLater {
        canvas.apply {
            updateBounds()
            drawShapes()
        }
    }
}