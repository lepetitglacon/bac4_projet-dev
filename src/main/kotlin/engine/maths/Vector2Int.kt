package engine.maths

import kotlin.math.pow
import kotlin.math.sqrt

class Vector2Int(var x: Int = 0, var y: Int = 0) {

    fun length() = sqrt((x.toDouble().pow(2.0) + y.toDouble().pow(2.0)))
    fun distance(v: Vector2Int) = minus(v).length()
//    fun normalize(): engine.maths.Vector2Int { if (length() == 0.0) return engine.maths.Vector2Int(); x.toDouble() /= length(); y.toDouble() /= length(); return this}
    fun dotProduct(v1: Vector2Int, v2: Vector2Int): Vector2Int { v1.x * v2.x + v1.y * v2.y; return this}

    operator fun plus(v: Vector2Int): Vector2Int { x += v.x; y += v.y; return this}
    operator fun plus(int: Int): Vector2Int { x += int; y += int; return this}
    operator fun minus(v: Vector2Int): Vector2Int { this + !v; return this}
    operator fun minus(int: Int): Vector2Int { x -= int; y -= int; return this}
    operator fun div(v: Vector2Int): Vector2Int { x /= v.x; y /= v.y; return this}
    operator fun div(int: Int): Vector2Int { x /= int; y /= int; return this}
    operator fun times(v: Vector2Int): Vector2Int { x *= v.x; y *= v.y; return this}
    operator fun times(int: Int): Vector2Int { x *= int; y *= int; return this}
    operator fun not(): Vector2Int { x = -x; y = -y; return this}
    operator fun unaryPlus() = plus(1)
    operator fun unaryMinus() = minus(1)

    override fun equals(other: Any?): Boolean {
        if (other !is Vector2Int) return false
        return x.toInt() == other.x.toInt() && y.toInt() == other.y.toInt()
    }

//    fun translateTo(v: engine.maths.Vector2Int, speed: Int) {
//        val newPos = engine.maths.Vector2Int()
//        val posMinusHero = engine.maths.Vector2Int()
//        posMinusHero.x = v.x - x
//        posMinusHero.y = v.y - y
//        newPos.x = x + posMinusHero.x / posMinusHero.length() * speed
//        newPos.y = y + posMinusHero.y / posMinusHero.length() * speed
//        x = newPos.x
//        y = newPos.y
//    }

    fun clone() = Vector2Int(x, y)
    fun toDouble(): Vector2 = Vector2(x.toDouble(), y.toDouble())
    override fun toString() = "$x $y"
    override fun hashCode(): Int = 31 * x.hashCode() + y.hashCode()
}