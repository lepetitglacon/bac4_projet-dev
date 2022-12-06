package engine.entities.weapon

import engine.entities.interfaces.Cooldownable
import engine.entities.weapon.projectiles.Projectile
import java.awt.Graphics2D

abstract class Weapon : Cooldownable {
    val projectiles: MutableList<Projectile> = mutableListOf()
    val maxProjectiles: Int = 1

    abstract fun fire()

    fun moveProjectiles() {
        projectiles.forEach { it.move() }
    }

    fun checkProjectilesCollisions() {
        projectiles.forEach { it.checkCollisionWithEnemies() }
    }

    fun drawProjectiles(g: Graphics2D) {
        projectiles.forEach { it.draw(g) }
    }
}