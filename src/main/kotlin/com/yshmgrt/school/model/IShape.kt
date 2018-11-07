package com.yshmgrt.school.model

import com.yshmgrt.school.util.P2D
import javafx.beans.property.SimpleStringProperty
import javafx.scene.canvas.GraphicsContext
import tornadofx.*
import javax.json.JsonObject

interface IShape : JsonModel{

    val type : String

    val nameProperty : SimpleStringProperty
    var name : String

    fun transform(f : (P2D) -> P2D) : IShape
    fun draw(parent : GraphicsContext)
    fun getForm() : Fragment
    fun create() : IShape
    fun xMin() : Double
    fun xMax() : Double
    fun yMin() : Double
    fun yMax() : Double
    override fun toJSON(json: JsonBuilder)
    override fun updateModel(json: JsonObject)
}