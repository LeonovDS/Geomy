package com.yshmgrt.school

import com.yshmgrt.school.model.IShape

interface ISolver {
    var params : List<Param>
    fun solve() : Any
}

data class Param(var desc : String = "", var f : (IShape) -> Boolean = {true}) {
    lateinit var shape : IShape
}