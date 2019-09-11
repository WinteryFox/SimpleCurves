package com.foxie.simplecurves

fun main() {
    val points = listOf(Point(-2.0, 10.0), Point(-1.0, 4.0), Point(1.0, 6.0), Point(2.0, 3.0))

    renderGraph(1080, 720, Pixel(255, 0, 0, 255), points, Type.CUBIC)
}