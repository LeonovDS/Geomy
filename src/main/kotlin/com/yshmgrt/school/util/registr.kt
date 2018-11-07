package com.yshmgrt.school.util

import com.yshmgrt.school.model.Circle
import com.yshmgrt.school.model.Point
import com.yshmgrt.school.model.Polyline
import com.yshmgrt.school.model.Segment
import com.yshmgrt.school.solver.APlusBSolver
import com.yshmgrt.school.solver.CircleAreaSolver

var types = hashMapOf(
        "point" to Point::class,
        "circle" to Circle::class,
        "polyline" to Polyline::class,
        "segment" to Segment::class
)

var solvers = listOf(
        APlusBSolver::class,
        CircleAreaSolver::class
)