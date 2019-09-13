package com.foxie.simplecurves

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.roundToInt

fun renderGraph(width: Int, height: Int, color: Pixel, points: List<Point>, type: Type): List<List<Pixel>> {
    val graph: List<Point> = interpolateGraph(points, width * height, type)
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    for (x in 0 until width)
        for (y in 0 until height)
            image.setRGB(x, y, 255.shl(24).or(255.shl(16)).or(255.shl(8)).or(255))

    val maxX = graph.maxBy(Point::x) ?: Point(0.0, 0.0)
    val minX = graph.minBy(Point::x) ?: Point(0.0, 0.0)
    val maxY = graph.maxBy(Point::y) ?: Point(0.0, 0.0)
    val minY = graph.minBy(Point::y) ?: Point(0.0, 0.0)
    graph.forEach {
        val x = (width - 1) * ((it.x + (0.0 - minX.x)) / (maxX.x - minX.x))
        val y = (height - 1) - (height - 1) * ((it.y + (0.0 - minY.y)) / (maxY.y - minY.y))
        image.setRGB(x.roundToInt(), y.toInt(), color.a.shl(24).or(color.r.shl(16)).or(color.g.shl(8)).or(color.b))
    }

    val output = File("image.png")
    ImageIO.write(image, "png", output)

    return emptyList()
}