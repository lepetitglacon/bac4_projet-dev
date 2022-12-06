package engine.entities.weapon

import engine.entities.factory.WeaponFactory
import engine.entities.weapon.projectiles.SwordSwing
import java.awt.Graphics2D
import java.time.Instant

class Sword : Weapon() {
    override var cooldownTime: Long = 3
    override var lastCooldownTime: Instant = Instant.now()

    override fun fire() {
        if (canMakeAction() && projectiles.size < maxProjectiles) {
            projectiles.add(WeaponFactory.createSwordSwing())
            // TODO fire event
        }
    }

}