package engine.math

import java.awt.Point
import kotlin.math.pow
import kotlin.math.sqrt

class Vector2(var x: Double = 0.0, var y: Double = 0.0) {
    constructor(point: Point) : this(point.x.toDouble(), point.y.toDouble())

    fun length() = sqrt((x * x + y * y))
    fun distance(v: Vector2) = minus(v).length()
    fun dotProduct(v1: Vector2, v2: Vector2): Vector2 { v1.x * v2.x + v1.y * v2.y; return this}
    fun normalize(): Vector2
    {
        if (length() == 0.0) return Vector2()
        div(length())
        return this
    }

    operator fun plus(v: Vector2): Vector2 { x += v.x; y += v.y; return this}
    operator fun plus(int: Int): Vector2 { x += int; y += int; return this}
    operator fun minus(v: Vector2): Vector2 { this + !v; return this}
    operator fun minus(int: Int): Vector2 { x -= int; y -= int; return this}
    operator fun div(v: Vector2): Vector2 { x /= v.x; y /= v.y; return this}
    operator fun div(int: Int): Vector2 { x /= int; y /= int; return this}
    operator fun div(int: Double): Vector2 { x /= int; y /= int; return this}
    operator fun times(v: Vector2): Vector2 { x *= v.x; y *= v.y; return this}
    operator fun times(int: Int): Vector2 { x *= int; y *= int; return this}
    operator fun not(): Vector2 { x = -x; y = -y; return this}
    operator fun unaryPlus() = plus(1)
    operator fun unaryMinus() = minus(1)

    override fun equals(other: Any?): Boolean {
        if (other !is Vector2) return false
        return x == other.x && y == other.y
    }

    fun translateTo(v: Vector2, speed: Int) {
        val newPos = Vector2()
        val posMinusHero = Vector2()
        posMinusHero.x = v.x - x
        posMinusHero.y = v.y - y
        newPos.x = x + posMinusHero.x / posMinusHero.length() * speed
        newPos.y = y + posMinusHero.y / posMinusHero.length() * speed
        x = newPos.x
        y = newPos.y
    }

    fun translatedTo(v: Vector2, speed: Int): Vector2 {
        val newPos = Vector2()
        val posMinusHero = Vector2()
        posMinusHero.x = v.x - x
        posMinusHero.y = v.y - y
        newPos.x = x + posMinusHero.x / posMinusHero.length() * speed
        newPos.y = y + posMinusHero.y / posMinusHero.length() * speed
        return Vector2(x + newPos.x, y + newPos.y)
    }

    fun clone() = Vector2(x, y)
    fun toDouble(): Vector2 = Vector2(x, y)
    fun int(): Vector2 = Vector2(x, y)
    override fun toString() = "$x $y"
    override fun hashCode(): Int = 31 * x.hashCode() + y.hashCode()
}