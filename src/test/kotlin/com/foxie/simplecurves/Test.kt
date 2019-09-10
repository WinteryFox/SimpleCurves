package com.foxie.simplecurves

fun main() {
    val points = listOf(Point(0.0, 0.0), Point(100.0, 100.0), Point(200.0, -200.0), Point(300.0, 0.0))

    renderGraph(1080, 720, Pixel(255, 0, 0, 255), points, Type.SMOOTH)
}