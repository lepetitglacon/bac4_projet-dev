package engine.entity.weapon.weapon

import engine.entity.sprite.Sprite
import engine.entity.weapon.Weapon
import engine.entity.weapon.WeaponFactory
import engine.entity.weapon.component.projectile.Projectile
import engine.math.Vec2
import engine.resource.SpriteFactory
import java.awt.Graphics2D
import java.time.Instant

class GrenadeLauncher() : Weapon() {
    override var sprite: Sprite = SpriteFactory.getEmptySprite()
    override var cooldown: Long = 3000 // ms

    val projectiles: MutableList<Projectile> = mutableListOf()

    // timers
    var timeBetweenShots: Long = 200 // ms
    var mustFireUntil: Long = 0
    var lastFire: Long = 0

    // projectiles
    var projectilesPerShot = 1
    var projectileHP = 1

//    override fun canFire(): Boolean {
//        return true
//    }

    fun fire() {
        mustFireUntil = Instant.now().toEpochMilli() + (projectilesPerShot * timeBetweenShots)
        println("fire until $mustFireUntil")
        lastFire = Instant.now().toEpochMilli() - 100
        lastCooldown = Instant.now().toEpochMilli()
    }

    fun fireSubShots() {
        if (mustFireUntil >= Instant.now().toEpochMilli() && lastFire + timeBetweenShots <= Instant.now().toEpochMilli()) {
            println("fire at $lastFire")
            projectiles.add(WeaponFactory.createProjectile(Vec2(), projectileHP))
            lastFire = Instant.now().toEpochMilli()
        }
    }

    override fun update() {
        if (canFire()) fire()
        fireSubShots()
        projectiles.forEach { it.update() }
    }

    override fun draw(g: Graphics2D) {
        projectiles.forEach { it.draw(g) }
    }
}