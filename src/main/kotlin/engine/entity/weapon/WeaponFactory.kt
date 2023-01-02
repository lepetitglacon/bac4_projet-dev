package engine.entity.weapon

import engine.GameEngine
import engine.entity.mob.enemy.Enemy
import engine.entity.weapon.component.Projectile
import engine.math.Vec2

object WeaponFactory {

    fun createProjectile() : Projectile
    {
        val p = Projectile()
        p.pos = GameEngine.game?.hero?.pos?.clone() ?: Vec2()
        p.width = 10
        p.height = 10

        // finding the closest enemy
        var lastDistance = GameEngine.game?.enemies?.firstOrNull()?.center()?.distance(GameEngine.game?.hero!!.center()) ?: 0.0
        var closest: Enemy? = GameEngine.game?.enemies?.firstOrNull()
        GameEngine.game?.enemies?.forEach {
            if (it.center().distance(GameEngine.game?.hero!!.center()) < lastDistance) {
                lastDistance = it.center().distance(GameEngine.game?.hero!!.center())
                closest = it
            }
        }

        val direction = closest?.center() ?: Vec2()
        println(direction)
        p.direction = (direction - GameEngine.game?.hero!!.center()).normalized()
        println(p.direction)
        return p
    }
}