package com.foxie.simplecurves

import kotlin.math.pow

enum class Type {
    LINEAR,
    SMOOTH,
    CUBIC
}

fun interpolateGraph(points: List<Point>, stepCount: Int, type: Type): List<Point> {
    val graph: MutableList<Point> = mutableListOf()

    /*for (i in 0 until points.size - 1) {
        val p1 = points[i]
        val p2 = points[i + 1]

        interpolate(p1, p2, stepCount, type).forEach { t -> graph.add(t) }
    }*/

    val matrix: MutableList<Vector> = mutableListOf()
    for (i in points.indices) {
        val a: MutableList<Double> = mutableListOf()
        for (n in points.indices) {
            a.add(points[i].x.pow(n))
        }
        matrix.add(Vector(a))
    }
    val values = solve(matrix, Vector(points.map(Point::y)))

    val stepWidth = (points[points.size - 1].x / points[0].x) / stepCount
    for (step in 0..stepCount) {
        val x = step * stepWidth

        var y: Double = 0.0
        for (i in values.data.indices) {
            y += values[i] * x.pow(i)
        }
        graph.add(Point(x, y))
    }

    return graph
}

fun solve(m: List<Vector>, a: Vector): Vector {
    val matrix = m.toMutableList()
    //for (i in 0 until matrix.size)
    //    matrix[i] = Vector(matrix[i].data.toMutableList().plus(a[i]))

    for (j in 0 until matrix.size - 1) {
        for (i in j + 1 until matrix.size) {
            matrix[i] -= matrix[j] * (matrix[i][j] / matrix[j][j])
        }
    }

    for (vec in matrix)
        println(vec)

    println("--")

    for (column in matrix.size - 2 downTo 0) {
        for ()
    }

    for (vec in matrix)
        println(vec)

    return Vector(emptyList())
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
            val x = step * stepWidth + p1.x
            val y = smoothStep(p1.x, p2.x, x) * (p2.y - p1.y) + p1.y
            interpolated.add(Point(x, y))
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