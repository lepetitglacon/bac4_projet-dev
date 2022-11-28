package weapons

import Entity
import Vector2
import interfaces.DrawableInterface
import java.awt.Color
import java.awt.Graphics2D

class Sword(
    override var width: Int,
    override var height: Int,
    override var position: Vector2,
    override var velocity: Vector2,
    override var speed: Int,
    override var angle: Int
) : Weapon(), DrawableInterface {
    init {
        position = Renderer.hero.position
    }
    override fun attack() {
        if (canFire()) {
            handleCooldown()
        }
    }
    override fun collides(e: Entity): Boolean {
        return isAttacking() && position.distance(e.position) - e.size / 2 < size
    }
    override fun draw(g: Graphics2D) {
        if (isAttacking()) {
            g.color = Color.MAGENTA
            g.fillArc(getDrawingX() - size / 2, getDrawingY() - size / 2, size * 2, size * 2, 0, 360)
        }
    }
    override fun checkCollision() {
        Renderer.entities.removeAll {
            val collides = collides(it)
            if (collides) {
                it.hp -= damage
            }
            collides && damage > it.hp
        }
    }
    override fun updatePosition(vector: Vector2) {

    }
    override fun fire() {

    }

    override fun goTo() {
        TODO("Not yet implemented")
    }

    override fun move() {
        TODO("Not yet implemented")
    }
}