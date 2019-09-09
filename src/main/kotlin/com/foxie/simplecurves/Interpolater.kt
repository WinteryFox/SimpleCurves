package com.foxie.simplecurves

enum class Type {
    LINEAR,
    SMOOTH
}

fun interpolate(p1: Point, p2: Point, stepCount: Int, type: Type): List<Point> {
    val interpolated: MutableList<Point> = mutableListOf()

    val stepWidth = (p2.x - p1.x) / stepCount
    for (step in 0..stepCount) {
        val x = p1.x + step * stepWidth

        if (type == Type.LINEAR) {
            val coefficient = (p2.y - p1.y) / (p2.x - p1.x)

            interpolated.add(Point(x, x * coefficient))
        } else if (type == Type.SMOOTH) {
            interpolated.add(Point(x, smoothStep(p1, p2, x)))
        }
    }

    return interpolated
}

fun smoothStep(p1: Point, p2: Point, x: Double): Double {
    val ret = clamp((x - p1.y) / (p2.y - p1.y), 0.0, 10.0)
    return ret * ret * ret * (ret * (ret * 6 - 15) + 10)
}

fun clamp(x: Double, lowerLimit: Double, upperLimit: Double): Double {
    if (x < lowerLimit)
        return lowerLimit
    else if (x > upperLimit)
        return upperLimit
    return x;
}