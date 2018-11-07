package com.yshmgrt.school.controller

import com.yshmgrt.school.model.*
import com.yshmgrt.school.util.P2D
import com.yshmgrt.school.util.extension
import com.yshmgrt.school.util.types
import javafx.collections.ObservableList
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File
import java.io.StringReader
import javax.json.*
import kotlin.reflect.full.createInstance

/**
 * Main controller of the project
 * @property shapes List of all shapes.
 * */
class GeoController : Controller() {
    var shapes : ObservableList<IShape> = observableList(
            Polyline(observableList(P2D(0.0, 0.0)), "A"),
            Polyline(observableList(P2D(1.1, 2.2), P2D(1.3, 2.4), P2D(3.0, 0.0)), "AB"),
            Point(P2D(0.1, 0.2), "C"),
            Segment(observableList(P2D(1.1, 2.2), P2D(1.3, 2.4)), "AQ"),
            Circle(0.0, 0.0, 1.0, "Circle")
    )

    var file : File? = null

    fun create() {
        chooseFile(filters = extension, mode = FileChooserMode.Save).apply {
            if (size > 0) this[0].apply {
                file = this
                this.createNewFile()
                shapes.clear()
            }
        }
    }

    fun load() {
        chooseFile(filters = extension).apply {
            if (size > 0) {
                shapes.clear()
                loadJsonArray(this[0].toPath()).forEach { it ->
                    shapes.add(types[it.asJsonObject().getString("type")]!!.createInstance().apply { updateModel(it.asJsonObject()) })
                }
            }
        }
    }

    fun saveAs() {
        chooseFile(filters = extension, mode = FileChooserMode.Save).apply {
            if (size > 0) this[0].apply {
                file = this
                this.createNewFile()
                val obj = Json.createArrayBuilder().apply {
                    shapes.forEach { it -> add(it.toJSON()) }
                }.build().toString()
                this.writeText(obj)
            }
        }
    }

    fun save() {
        file!!.apply {
            this.createNewFile()
            val obj = Json.createArrayBuilder().apply {
                shapes.forEach { it -> add(it.toJSON()) }
            }.build().toString()
            this.writeText(obj)
        }
    }

    fun add(shape : IShape) {
        shapes.add(shape)
    }
}