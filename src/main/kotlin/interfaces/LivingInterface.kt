package interfaces

import java.awt.Color
import java.awt.Graphics2D

interface LivingInterface : DrawableInterface {
    var maxHp: Int
    var hp: Int
    var regeneration: Int

    fun isDead() = hp <= 0

    fun drawHealthbar(g: Graphics2D) {
        g.color = Color.BLACK
        g.fillRect(getDrawingX(), getDrawingY() - 10, size, 5)

        when (true) {
            (hp < 25) -> g.color = Color.RED
            (hp < 50) -> g.color = Color.YELLOW
            else -> g.color = Color.GREEN
        }
        g.fillRect(getDrawingX() + 1, getDrawingY() - 9, (size * hp / 100) - 2, 3)
    }
}