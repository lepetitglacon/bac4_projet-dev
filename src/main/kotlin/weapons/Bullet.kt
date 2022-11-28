package weapons

import Entity
import Vector2
import interfaces.DrawableInterface
import java.awt.Color
import java.awt.Graphics2D

class Bullet(
    override var width: Int,
    override var height: Int,
    override var position: Vector2,
    override var velocity: Vector2,
    override var speed: Int
) : Weapon(), DrawableInterface {
    val target: Entity = Renderer.entities.first()
    init {
        position = Renderer.hero.position.clone()
        attackSpeed = 50
        size = 10
        cooldown = 1
    }
    override fun attack() {
        Renderer.entities.forEach {
            if (collides(it)) {
                it.hp -= damage
                needRemoval = true
            }
        }
        updatePosition(Vector2())
    }
    override fun collides(e: Entity): Boolean {
        return position.distance(e.position) - e.size / 2 < size
    }
    override fun draw(g: Graphics2D) {
        g.color = Color.LIGHT_GRAY
        g.fillOval(getDrawingX(), getDrawingY(), size, size)
//        g.fillOval(150, 150, range, range)
        g.drawString("${position.x.toInt()} ${position.y.toInt()}", getDrawingX(), getDrawingY() - 10)
        g.color = Color.BLACK
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
        var closest = Renderer.entities.first()
        Renderer.entities.forEach {
            if (position.distance(it.position) < position.distance(closest.position)) {
                closest = it
            }
        }
        position.translateTo(closest.position, speed)
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