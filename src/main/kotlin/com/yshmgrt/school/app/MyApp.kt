package com.yshmgrt.school.app

import com.yshmgrt.school.controller.GeoController
import com.yshmgrt.school.view.MainView
import javafx.stage.Stage
import tornadofx.App

class MyApp: App(MainView::class, Styles::class) {
    override fun start(stage: Stage) {
        super.start(stage)
    }
    override fun stop() {
        super.stop()
    }
}