package com.foxie.simplecurves

import kotlin.math.pow

data class Vector(val data: List<Double>) {
    val size = data.size

    operator fun get(index: Int): Double {
        return data[index]
    }

    operator fun plus(increment: Double): Vector {
        return Vector(data.map { t -> t + increment })
    }

    operator fun minus(decrement: Double): Vector {
        return Vector(data.map { t -> t - decrement })
    }

    operator fun times(scalar: Double): Vector {
        return Vector(data.map { t -> t * scalar })
    }

    operator fun div(divider: Double): Vector {
        return Vector(data.map { t -> t / divider })
    }

    fun pow(power: Double): Vector {
        return Vector(data.map { it.pow(power) })
    }

    operator fun plus(increment: Vector): Vector {
        require(increment.size == data.size) { "Vectors must both be of the same size" }

        return Vector(data.mapIndexed { i, t -> t + increment[i] })
    }

    operator fun minus(increment: Vector): Vector {
        require(increment.size == data.size) { "Vectors must both be of the same size" }

        return Vector(data.mapIndexed { i, t -> t - increment[i] })
    }

    operator fun times(other: Vector): Vector {
        require(other.size == data.size) { "Vectors must both be of the same size" }

        return Vector(data.mapIndexed { i, t -> t * other[i] })
    }

    operator fun div(other: Vector): Vector {
        require(other.size == data.size) { "Vectors must both be of the same size" }

        return Vector(data.mapIndexed { i, t -> t / other[i] })
    }

    fun pow(power: Vector): Vector {
        require(power.size == data.size) { "Vectors must both be of the same size" }

        return Vector(data.mapIndexed { i, t -> t.pow(power[i]) })
    }
}