package com.foxie.simplecurves

fun main() {
    val p1 = Point(0.0, 0.0)
    val p2 = Point(10.0, 10.0)

    val linearInterpolation = interpolateLinear(p1, p2, 10)

    linearInterpolation.forEachIndexed { i, t -> println("$i: ${t.x}, ${t.y}")}
}