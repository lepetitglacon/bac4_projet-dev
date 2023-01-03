package engine.entity.weapon.weapon

import engine.GameEngine
import engine.entity.weapon.Weapon
import engine.entity.weapon.WeaponFactory
import engine.entity.weapon.component.Projectile
import java.awt.Graphics2D
import java.time.Instant

class Gun : Weapon() {
    val projectiles: MutableList<Projectile> = mutableListOf()

    fun fire() {
        projectiles.add(WeaponFactory.createProjectile(GameEngine.game?.hero?.center()!!))
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