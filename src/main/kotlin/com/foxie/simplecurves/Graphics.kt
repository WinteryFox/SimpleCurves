package com.foxie.simplecurves

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.roundToInt

fun renderGraph(width: Int, height: Int, color: Color, background: Color, points: List<Point>, type: Type) {
    val graph: List<Point> = interpolateGraph(points, width * height, type)
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    for (x in 0 until width)
        for (y in 0 until height)
            image.setRGB(
                x,
                y,
                background.alpha.shl(24).or(background.red.shl(16)).or(background.green.shl(8)).or(background.blue)
            )

    val maxX = graph.maxBy(Point::x) ?: Point(0.0, 0.0)
    val minX = graph.minBy(Point::x) ?: Point(0.0, 0.0)
    val maxY = graph.maxBy(Point::y) ?: Point(0.0, 0.0)
    val minY = graph.minBy(Point::y) ?: Point(0.0, 0.0)
    graph.forEach {
        val x = (width - 1) * ((it.x + (0.0 - minX.x)) / (maxX.x - minX.x))
        val y = (height - 1) - (height - 1) * ((it.y + (0.0 - minY.y)) / (maxY.y - minY.y))
        image.setRGB(
            x.roundToInt(),
            y.toInt(),
            color.alpha.shl(24).or(color.red.shl(16)).or(color.green.shl(8)).or(color.blue)
        )
    }

    val output = File("image.png")
    ImageIO.write(image, "png", output)
}