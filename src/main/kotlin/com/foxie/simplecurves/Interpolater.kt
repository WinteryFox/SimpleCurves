package com.foxie.simplecurves

enum class Type {
    LINEAR,
    SMOOTH
}

fun interpolateGraph(points: List<Point>, stepCount: Int, type: Type): List<Point> {
    val graph: MutableList<Point> = mutableListOf()

    var i = 0
    while (i < points.size - 1) {
        val p1 = points[i]
        val p2 = points[i + 1]

        graph.add(p1)
        interpolate(p1, p2, stepCount, type).forEach { t -> graph.add(t) }

        i++
    }
    graph.add(points[points.size - 1])

    return graph
}

fun interpolate(p1: Point, p2: Point, stepCount: Int, type: Type): List<Point> {
    require(stepCount >= 2) { "Step count must be bigger than 2" }

    val interpolated: MutableList<Point> = mutableListOf()
    val stepWidth = (p2.x - p1.x) / stepCount

    for (step in 1 until stepCount) {
        val x = p1.x + step * stepWidth

        if (type == Type.LINEAR) {
            val coefficient = (p2.y - p1.y) / (p2.x - p1.x)

            interpolated.add(Point(x, x * coefficient))
        } else if (type == Type.SMOOTH) {
            interpolated.add(Point(x, p1.y + smoothStep(p1, p2, x) * (p2.y - p1.y)))
        }
    }

    return interpolated
}

fun smoothStep(p1: Point, p2: Point, x: Double): Double {
    val ret = clamp((x - p1.x) / (p2.x - p1.x), 0.0, 1.0)
    return (ret * ret * ret * (ret * (ret * 6 - 15) + 10))
}

fun clamp(x: Double, lowerLimit: Double, upperLimit: Double): Double {
    if (x < lowerLimit)
        return lowerLimit
    else if (x > upperLimit)
        return upperLimit
    return x
}