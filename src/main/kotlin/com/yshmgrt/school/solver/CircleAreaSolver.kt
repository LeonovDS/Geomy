package com.yshmgrt.school.solver

import com.yshmgrt.school.model.Circle
import com.yshmgrt.school.model.ShapeModel
import com.yshmgrt.school.util.sqr
import com.yshmgrt.school.view.ChooseMenuView
import javafx.scene.control.Label
import tornadofx.*

class CircleAreaSolver : ISolver {
    override var name = "Circle area"
    val model = ShapeModel()
    override fun solve(): Double {
        return (model.item as Circle).r.sqr() * Math.PI
    }

    override fun draw() = object : Fragment() {
        private lateinit var resultLabel : Label
        override val root = form {
            label("Calculate area of circle")
            fieldset {
                hbox {
                    field("Circle") {textfield(model.name) { isEditable = false }}
                    button("Chose").action {
                        ChooseMenuView { it is Circle }.apply {
                            openModal(block = true)
                            model.item = output()?.item ?: model.item
                        }
                    }
                }
            }
            fieldset {
                button("Calculate").action { resultLabel.text = solve().toString() }
                resultLabel = label("")
            }
        }
    }
}