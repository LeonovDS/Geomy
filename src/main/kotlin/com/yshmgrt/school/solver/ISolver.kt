package com.yshmgrt.school.solver

import tornadofx.*

interface ISolver {
    var name : String
    fun solve() : Any
    fun draw() : Fragment
}