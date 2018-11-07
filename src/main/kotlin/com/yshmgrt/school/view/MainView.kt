package com.yshmgrt.school.view

import com.yshmgrt.school.app.Styles
import com.yshmgrt.school.controller.GeoController
import com.yshmgrt.school.model.IShape
import com.yshmgrt.school.model.Point
import com.yshmgrt.school.model.ShapeModel
import com.yshmgrt.school.util.*
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.control.ListView
import javafx.scene.image.Image
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
            drawer(side = Side.LEFT) {
                minWidth = 200.0
                item("Shapes") {
                    list = listview(controller.shapes) {
                        addClass(Styles.listStyle)
                        cellFormat { shape ->
                            graphic = hbox {
                                label(shape.nameProperty).addClass(Styles.listStyle).apply {
                                    alignment = Pos.CENTER_LEFT
                                }
                                imageview(Image("delete.png")).setOnMouseClicked {
                                    controller.shapes.remove(index, index + 1)
                                    updateLists()
                                }.apply {
                                    alignment = Pos.CENTER_RIGHT
                                }
                                setOnMouseClicked { updateLists() }
                            }
                        }
                        bindSelected(model)
                        isFillHeight = true
                    }
                }
                item("Solvers") {
                    listview(solvers.observable()) {
                        cellFormat {
                            text = item.createInstance().name
                        }
                        onUserSelect(clickCount = 1) {
                            changeFragment(it.createInstance().draw())
                        }
                        addClass(Styles.listStyle)
                    }
                }
            }
            vbox {
                canvas = GeometryCanvas(controller)
                add(canvas)
                listview(types.values.toList().observable()) {
                    addClass(Styles.listStyle)
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
        changeFragment(model.item?.getForm())
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

    private fun changeFragment(f : Fragment?) {
        fragment?.removeFromParent()
        fragment = f ?: EmptyFragment()
        hb.add(fragment!!)
    }
}