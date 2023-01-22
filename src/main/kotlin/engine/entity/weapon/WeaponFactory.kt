package engine.entity.weapon

import engine.GameEngine
import engine.entity.mob.enemy.Enemy
import engine.entity.weapon.component.projectile.Projectile
import engine.math.Vec2

object WeaponFactory {

    fun createProjectile(direction: Vec2?, hp: Int) : Projectile
    {
        val p = Projectile()
        p.pos = GameEngine.game?.hero?.pos?.clone() ?: Vec2()
        p.width = 10
        p.height = 10
        p.hp = hp.toDouble()
        p.direction = direction ?: findClosestEnemyDirection()
        return p
    }

    fun findClosestEnemyDirection(): Vec2 {
        // finding the closest enemy
        var lastDistance = Double.MAX_VALUE
        var closest: Enemy? = GameEngine.game?.enemies?.firstOrNull()
        GameEngine.game?.enemies?.forEach {
            if (it.center().distance(GameEngine.game?.hero!!.center()) < lastDistance) {
                lastDistance = it.center().distance(GameEngine.game?.hero!!.center())
                closest = it
            }
        }

        val dir = closest?.center() ?: Vec2()
        dir.x += closest?.width?.div(2) ?: 0
        dir.y += closest?.height?.div(2) ?: 0
        return (dir - GameEngine.game?.hero!!.center()).normalized()
    }
}