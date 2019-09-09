package com.foxie.simplecurves

enum class Type {
    LINEAR,
    SMOOTH
}

fun interpolateGraph(points: List<Point>, stepCount: Int, type: Type): List<Point> {
    val graph: MutableList<Point> = mutableListOf()

    for (i in 0 until points.size - 1) {
        val p1 = points[i]
        val p2 = points[i + 1]

        interpolate(p1, p2, stepCount, type).forEach { t -> graph.add(t) }
    }

    return graph
}

fun interpolate(p1: Point, p2: Point, stepCount: Int, type: Type): List<Point> {
    require(stepCount >= 3) { "Step count must be bigger than 3" }

    val interpolated: MutableList<Point> = mutableListOf()
    val stepWidth = (p2.x - p1.x) / stepCount

    for (step in 0..stepCount) {
        if (type == Type.LINEAR) {
            val coefficient = (p2.y - p1.y) / (p2.x - p1.x)
            val x = step * stepWidth
            val y = coefficient * x + p1.y

            interpolated.add(Point(x + p1.x, y))
        } else if (type == Type.SMOOTH) {
            val x = step * stepWidth
            val smooth = smoothStep(0.0, p2.x - p1.x, x)
            val normalized = if (p1.y > p2.y)
                1.0 - smooth
            else
                smooth
            val y = normalized * -(p2.y - p1.x)

            interpolated.add(Point(x + p1.x, y))
        }
    }

    return interpolated
}

fun smoothStep(edge1: Double, edge2: Double, x: Double): Double {
    val ret = clamp((x - edge1) / (edge2 - edge1), 0.0, 1.0)
    return ret * ret * ret * (ret * (ret * 6 - 15) + 10)
}

fun clamp(x: Double, lowerLimit: Double, upperLimit: Double): Double {
    if (x < lowerLimit)
        return lowerLimit
    else if (x > upperLimit)
        return upperLimit
    return x
}