package com.foxie.simplecurves

fun main() {
    val points = listOf(Point(0.0, 0.0), Point(10.0, 10.0), Point(20.0, 50.0), Point(30.0, 200.0))
    val graph = interpolateGraph(points, 10, Type.SMOOTH)

    graph.forEachIndexed { i, t -> println("$i: ${t.x}, ${t.y}") }
}