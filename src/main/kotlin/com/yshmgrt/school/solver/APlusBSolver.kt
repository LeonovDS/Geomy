package com.yshmgrt.school.solver

import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.control.Label
import tornadofx.*

class APlusBSolver : ISolver {
    override var name = "Sum"
    val a = SimpleDoubleProperty(0.0)
    val b = SimpleDoubleProperty(0.0)
    override fun solve(): Double {
        return a.value + b.value
    }

    override fun draw() = object : Fragment() {
        private lateinit var resultLabel : Label
        override val root = form {
            label("Calculate sum of two numbers")
            fieldset {
                field("a") { textfield(a) }
                field("b") { textfield(b) }
            }
            fieldset {
                button("Calculate").action { resultLabel.text = solve().toString() }
                resultLabel = label("")
            }
        }
    }
}