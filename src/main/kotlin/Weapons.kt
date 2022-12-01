import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D

class Sword() : Weapon() {

    init {
        position = Renderer.hero.position
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

    init {
        position = Renderer.hero.position
        attackSpeed = 50
        range = 10
    }

    override fun attack() {

        updatePosition(Vector2())
    }

    override fun collides(e: Entity): Boolean {
        return position.distance(e.position) - e.size / 2 < range
    }

    override fun draw(g: Graphics2D) {
        g.color = Color.YELLOW
//        g.fillOval(getDrawingX(), getDrawingY(), range, range)
        g.fillOval(150, 150, range, range)
        g.drawString("${position.x} ${position.y}", getDrawingX(), getDrawingY() - 10)
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
        vector.normalize()
        velocity.x += (vector.x * speed).toInt()
        velocity.y += (vector.y * speed).toInt()

        position.x += velocity.x
        position.y += velocity.y
    }

    override fun fire() {

    }
}