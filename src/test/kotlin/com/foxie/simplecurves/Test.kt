package com.foxie.simplecurves

fun main() {
    val points = listOf(Point(0.0, 0.0), Point(100.0, 100.0), Point(200.0, 0.0))

    renderGraph(1920, 1080, Pixel(255, 0, 0, 255), points, Type.SMOOTH)
}