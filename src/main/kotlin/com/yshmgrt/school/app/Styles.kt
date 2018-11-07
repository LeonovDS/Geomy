package com.yshmgrt.school.app

import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val listStyle by cssclass()

        val bgColor = c("#eeeeee")
        val accColor = c("#00ddaf")
        val textColor = c("#111111")
        val lightColor = c("#bbbbbb")
    }

    init {
        listStyle {
            textFill = textColor
            fontSize = Dimension(14.0, Dimension.LinearUnits.pt)
            and (listView) {
                backgroundColor += bgColor
                listCell {
                    backgroundColor += bgColor
                    borderColor += box(lightColor)
                    borderWidth += box(0.5.px, 0.px)
                    cellHeight = Dimension(40.0, Dimension.LinearUnits.px)
                    and(horizontal) {
                        cellHeight = Dimension(30.0, Dimension.LinearUnits.px)
                    }
                    and(selected) {
                        backgroundColor = multi(accColor)
                    }
                    and(empty) {
                        borderWidth += box(0.px)
                    }
                }

            }
        }
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
    }
}