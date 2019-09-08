package com.foxie.simplecurves

fun interpolateLinear(p1: Point, p2: Point, stepCount: Int): List<Point> {
    val interpolated: MutableList<Point> = mutableListOf()

    val coefficient = (p2.y - p1.y) / (p2.x - p1.x)
    val width = (p2.x - p1.x) / stepCount

    for (step in 0..stepCount) {
        interpolated.add(Point(p1.x + step * width, p1.x + step * width * coefficient))
    }

    return interpolated
}