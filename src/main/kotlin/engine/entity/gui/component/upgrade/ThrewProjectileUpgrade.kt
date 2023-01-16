package engine.entity.gui.component.upgrade

import engine.EngineState
import engine.GameEngine
import engine.entity.weapon.weapon.GrenadeLauncher
import engine.entity.weapon.weapon.Gun

class ThrewProjectileUpgrade() : Upgrade("Make projectile go threw 1 more enemy") {
    override val maxLevel: Int = 2

    override fun onClick() {
        GameEngine.game?.hero?.weapons?.forEach {
            if (it is Gun) it.projectileHP++
            if (it is GrenadeLauncher) it.projectileHP++
        }
        GameEngine.state = EngineState.PLAY
    }
}