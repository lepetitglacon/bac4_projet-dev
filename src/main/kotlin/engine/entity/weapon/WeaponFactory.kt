package engine.entity.weapon

import engine.GameEngine
import engine.entity.weapon.component.Projectile
import engine.math.Vector2

object WeaponFactory {

    fun createProjectile() : Projectile
    {
        val p = Projectile()
        p.x = GameEngine.game.hero.x
        p.y = GameEngine.game.hero.y
        p.width = 10
        p.height = 10
        val e = GameEngine.game.enemies.first()
        p.direction = (e.center() - GameEngine.game.hero.center()).normalize()
        return p
    }
}