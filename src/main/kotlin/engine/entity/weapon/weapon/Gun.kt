package engine.entity.weapon.weapon

import engine.entity.weapon.Weapon
import engine.entity.weapon.WeaponEffect
import engine.entity.weapon.WeaponFactory
import engine.entity.weapon.component.Projectile
import java.awt.Graphics2D
import java.time.Instant

class Gun : Weapon() {
    val projectiles: MutableList<Projectile> = mutableListOf()

    fun fire() {
        println("gun fire")
        projectiles.add(WeaponFactory.createProjectile())
        lastCooldown = Instant.now()
    }

    override fun update() {
        if (canFire()) fire()
        projectiles.forEach { it.update() }
    }

    override fun draw(g: Graphics2D) {
        projectiles.forEach { it.draw(g) }
    }
}