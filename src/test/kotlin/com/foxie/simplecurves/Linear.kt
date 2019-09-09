package com.foxie.simplecurves

fun main() {
    val p1 = Point(0.0, 0.0)
    val p2 = Point(10.0, 10.0)

    val interpolated = interpolate(p1, p2, 10, Type.LINEAR)

    interpolated.forEachIndexed { i, t -> println("$i: ${t.x}, ${t.y}")}
}