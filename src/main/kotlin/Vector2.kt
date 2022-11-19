import java.awt.Graphics2D
import kotlin.math.pow
import kotlin.math.sqrt

class Vector2(var x: Double = 0.0, var y: Double = 0.0) {

    fun lenght(): Double {
        return sqrt((x.pow(2.0) + y.pow(2.0)))
    }

    fun distance(vector: Vector2): Double {
        return minus(this, vector).lenght()
    }

    fun normalize() {
        val len = lenght()
        if (len == 0.0) return

        x /= len
        y /= len
    }

    fun add(vector: Vector2) {
        x += vector.x
        y += vector.y
    }

    fun minus(vector: Vector2) {
        x -= vector.x
        y -= vector.y
    }

    fun times(speed: Vector2) {
        x *= speed.x
        y *= speed.y
    }

    override fun toString(): String {
        return "$x $y lenght=${lenght()}"
    }

    companion object {

        fun add(vector1: Vector2, vector2: Vector2): Vector2 {
            return Vector2(vector1.x+vector2.x, vector1.y+vector2.y)
        }

        fun minus(vector1: Vector2, vector2: Vector2): Vector2 {
            return Vector2(vector1.x - vector2.x, vector1.y - vector2.y)
        }

        fun times(vector1: Vector2, vector2: Vector2): Vector2 {
            return Vector2(vector1.x - vector2.x, vector1.y - vector2.y)
        }

        fun normalize(vector: Vector2): Vector2 {
            val len = vector.lenght()
            if (len == 0.0) return Vector2()

            vector.x /= len
            vector.y /= len
            return vector
        }
    }

}