package com.yshmgrt.school.view

import com.yshmgrt.school.controller.GeoController
import com.yshmgrt.school.util.P2D
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import java.lang.Double.max
import java.lang.Double.min

class GeometryCanvas(val controller: GeoController) : Canvas() {

    private var xMin = 0.0
    private var xMax = 0.0
    private var yMin = 0.0
    private var yMax = 0.0
    private var transform : (P2D) -> P2D = { it -> it}

    private val kBorder = 0.85

    fun updateBounds() {
        with(controller) {
            if (shapes.size > 0) {
                xMin = shapes[0].src[0].x
                xMax = shapes[0].src[0].x
                yMin = shapes[0].src[0].y
                yMax = shapes[0].src[0].y
            }
            shapes.forEach { i ->
                i.src.forEach {
                    xMin = min(xMin, it.x)
                    xMax = max(xMax, it.x)
                    yMin = min(yMin, it.y)
                    yMax = max(yMax, it.y)
                }
            }
        }
        val width = xMax - xMin
        val height = yMax - yMin
        var scale = min(this.width / width, this.height / height)
        scale *= kBorder
        val centerX = (xMin + xMax) / 2
        val centerY = (yMin + yMax) / 2
        transform = {it ->
            P2D( this@GeometryCanvas.width / 2 + scale * (it.x - centerX),
                    this@GeometryCanvas.height / 2 - scale * (it.y - centerY))
        }
    }

    fun drawShapes() {
        clear()
        controller.shapes.forEach { i ->
            i.transform(transform).draw(graphicsContext2D)
        }
    }

    private fun clear() {
        width = 500.0
        height = 500.0
        graphicsContext2D?.apply {
            fill = Color.WHITE
            fillRect(0.0, 0.0, width, height)
        }
    }
}