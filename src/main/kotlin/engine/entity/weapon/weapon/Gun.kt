package engine.entity.weapon.weapon

import engine.GameEngine
import engine.entity.weapon.Weapon
import engine.entity.weapon.WeaponFactory
import engine.entity.weapon.component.Projectile
import java.awt.Graphics2D
import java.time.Instant

class Gun : Weapon() {
    override var cooldown: Long = 1000 // ms
    val projectiles: MutableList<Projectile> = mutableListOf()

    // timers
    var timeBetweenShots = 250 // ms

    // projectile
    var projectilesPerShot = 1
    var projectileHP = 1

    fun fire() {
        var shotTime: Instant? = null
        for (i in 0..projectilesPerShot) {
            if (shotTime == null || shotTime.toEpochMilli() + timeBetweenShots >= Instant.now().toEpochMilli()) {
                projectiles.add(WeaponFactory.createProjectile(null, projectileHP))
                shotTime = Instant.now()
            }
        }
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