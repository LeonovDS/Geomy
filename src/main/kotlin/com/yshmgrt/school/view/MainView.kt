package com.yshmgrt.school.view

import com.yshmgrt.school.controller.GeoController
import com.yshmgrt.school.model.IShape
import com.yshmgrt.school.model.Polyline
import com.yshmgrt.school.model.ShapeModel
import com.yshmgrt.school.util.*
import javafx.geometry.Orientation
import javafx.scene.control.ListView
import javafx.scene.layout.HBox
import tornadofx.*
import kotlin.reflect.full.createInstance


class MainView : View("Hello TornadoFX") {

    private val controller : GeoController by inject()
    private val model = ShapeModel()
    private var canvas : GeometryCanvas by singleAssign()
    private var list : ListView<IShape> by singleAssign()
    private var fragment : Fragment? = null
    private lateinit var hb : HBox

    override val root = vbox {
        minHeight = 600.0
        minWidth = 1000.0
        menubar {
            menu("File") {
                item("New").action {
                    controller.create()
                    updateLists()
                }
                item("Open").action {
                    controller.load()
                    updateLists()
                }
                item("Save").action { controller.save() }
                item("Save as").action { controller.saveAs() }
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
                            print(controller.shapes[controller.shapes.size - 1].toJSON().toString())
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
                listview(types.values.toList().observable()) {
                    orientation = Orientation.HORIZONTAL
                    cellFormat {
                        graphic = button(item?.simpleName ?: "") {
                            action {
                                controller.add(item.createInstance())
                                updateLists()
                            }
                        }
                    }
                }
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

    override fun onRefresh() {
        super.onRefresh()
        updateLists()
    }

    override fun onSave() {
        super.onSave()
        controller.save()
    }

    override fun onDock() {
        super.onDock()
        updateLists()
    }
}