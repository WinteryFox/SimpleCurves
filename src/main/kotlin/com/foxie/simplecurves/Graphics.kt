package com.foxie.simplecurves

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.roundToInt

fun renderGraph(width: Int, height: Int, color: Pixel, points: List<Point>, type: Type): List<List<Pixel>> {
    val graph: List<Point> = interpolateGraph(points, width / (points.size - 1), type)
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)

    graph.forEach {
        val x = it.x.roundToInt()
        val y = it.y.toInt()
        image.setRGB(x, height - 1 - y, color.a.shl(24).or(color.r.shl(16)).or(color.g.shl(8)).or(color.b))
    }

    val output = File("image.png")
    ImageIO.write(image, "png", output)

    return emptyList()
}