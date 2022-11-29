import engine.Renderer
import engine.Vector2
import java.awt.Color
import java.awt.Graphics2D

class Sword() : Weapon() {

    init {
        position = Renderer.game.hero.position
    }

    override fun attack() {
        if (canFire()) {
            handleCooldown()
        }
    }

    override fun collides(e: Entity): Boolean {
        return isAttacking() && position.distance(e.position) - e.size / 2 < range
    }

    override fun draw(g: Graphics2D) {
        if (isAttacking()) {
            g.color = Color.MAGENTA
            g.fillArc(getDrawingX() - range / 2, getDrawingY() - range / 2, range * 2, range * 2, 0, 360)
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
}

class Bullet() : Weapon() {
    val size: Int
    init {
        attackSpeed = 50
        size = 10
        damage = 25
    }

    override fun attack() {
        updatePosition(Vector2())
        Renderer.entities.forEach {
            if (collides(it)) {
                it.hp -= damage
                needRemoval = true
            }
        }
    }

    override fun collides(e: Entity): Boolean {
        return position.distance(e.position) - e.size / 2 < range
    }

    override fun draw(g: Graphics2D) {
        g.color = Color.RED
        g.fillOval(getDrawingX() + size/2, getDrawingY() + size/2, size, size)
//        g.fillOval(150, 150, range, range)
        g.drawString("${position.x.toInt()} ${position.y.toInt()}", getDrawingX(), getDrawingY() - 10)
        g.color = Color.BLACK
    }

    override fun checkCollision() {
        Renderer.entities.removeAll {
            val collides = collides(it)
            if (collides) {
                it.hp -= damage
                needRemoval = true
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
        TODO("Not yet implemented")
    }
}