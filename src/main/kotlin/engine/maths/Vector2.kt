package engine.maths

import kotlin.math.pow
import kotlin.math.sqrt

class Vector2(var x: Double = 0.0, var y: Double = 0.0) {

    fun length() = sqrt((x.pow(2.0) + y.pow(2.0)))
    fun distance(v: Vector2) = minus(v).length()

    /** Normalize this vector */
    fun normalize(): Vector2 { if (length() == 0.0) return Vector2(); x /= length(); y /= length(); return this}
    /** Return the normalized vector (don't modify OG one) */
    fun normalized(): Vector2 { if (length() == 0.0) return Vector2(); return Vector2(x / length(), y / length())}
    fun dotProduct(v1: Vector2, v2: Vector2): Vector2 { v1.x * v2.x + v1.y * v2.y; return this}

    operator fun plus(v: Vector2): Vector2 { return Vector2(x + v.x, y + v.y) }
    operator fun plus(int: Int): Vector2 { return Vector2(x + int, y + int)}
    operator fun minus(v: Vector2): Vector2 { return Vector2(x + -v.x, y + -v.y)}
    operator fun minus(int: Int): Vector2 { return Vector2(x - int, y - int)}
    operator fun div(v: Vector2): Vector2 { return Vector2(x / v.x, y / v.y)}
    operator fun div(int: Int): Vector2 { return Vector2(x / int, y / int)}
    operator fun div(int: Double): Vector2 { return Vector2(x / int, y / int)}
    operator fun times(v: Vector2): Vector2 { return Vector2(x * v.x, y * v.y)}
    operator fun times(int: Int): Vector2 { return Vector2(x * int, y * int)}
    operator fun times(double: Double): Vector2 { return Vector2(x * double, y * double)}
    operator fun not(): Vector2 { return Vector2(-x, -y)}
    operator fun unaryPlus() = plus(1)
    operator fun unaryMinus() = minus(1)

    override fun equals(other: Any?): Boolean {
        if (other !is Vector2) return false
        return x.toInt() == other.x.toInt() && y.toInt() == other.y.toInt()
    }

    fun translateTo(v: Vector2, speed: Double) {
        val newPos = Vector2()
        val posMinusHero = Vector2()
        posMinusHero.x = v.x - x
        posMinusHero.y = v.y - y
        newPos.x = x + posMinusHero.x / posMinusHero.length() * speed
        newPos.y = y + posMinusHero.y / posMinusHero.length() * speed
        x = newPos.x
        y = newPos.y
    }

    fun translatedTo(v: Vector2, speed: Double): Vector2 {
        val newPos = Vector2()
        val posMinusHero = Vector2()
        posMinusHero.x = v.x - x
        posMinusHero.y = v.y - y
        newPos.x = x + posMinusHero.x / posMinusHero.length() * speed
        newPos.y = y + posMinusHero.y / posMinusHero.length() * speed
        return Vector2(x + newPos.x, y + newPos.y)
    }

    fun clone() = Vector2(x, y)
    fun toInt(): Vector2Int = Vector2Int(x.toInt(), y.toInt())
    override fun toString() = "$x $y"
    override fun hashCode(): Int = 31 * x.hashCode() + y.hashCode()
}