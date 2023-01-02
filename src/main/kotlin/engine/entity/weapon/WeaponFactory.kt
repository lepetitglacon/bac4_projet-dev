package engine.entity.weapon

import engine.GameEngine
import engine.entity.weapon.component.Projectile
import engine.math.Vec2

object WeaponFactory {

    fun createProjectile() : Projectile
    {
        val p = Projectile()
        p.pos = GameEngine.game?.hero?.pos?.clone() ?: Vec2()
        p.width = 10
        p.height = 10
        val direction = GameEngine.game?.enemies?.firstOrNull()?.center() ?: Vec2()
        p.direction = (direction - GameEngine.game?.hero!!.center()).normalized()
        return p
    }
}