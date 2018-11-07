package com.yshmgrt.school.util

import com.yshmgrt.school.model.Circle
import com.yshmgrt.school.model.Point
import com.yshmgrt.school.model.Polyline
import com.yshmgrt.school.model.Segment

var types = hashMapOf(
        "point" to Point::class,
        "circle" to Circle::class,
        "polyline" to Polyline::class,
        "segment" to Segment::class
)