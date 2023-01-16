package engine.entity.gui.component.upgrade

import engine.EngineState
import engine.GameEngine
import engine.entity.weapon.weapon.GrenadeLauncher
import engine.entity.weapon.weapon.Gun

class DoubleProjectileUpgrade() : Upgrade("Double weapons projectiles") {
    override val maxLevel: Int = 4

    override fun onClick() {
        GameEngine.game?.hero?.weapons?.forEach {
            if (it is Gun) it.projectilesPerShot *= 2
            if (it is GrenadeLauncher) it.projectilesPerShot *= 2
        }
        GameEngine.state = EngineState.PLAY
    }
}