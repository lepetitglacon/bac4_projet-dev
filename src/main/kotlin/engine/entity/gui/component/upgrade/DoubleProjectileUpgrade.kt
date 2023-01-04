package engine.entity.gui.component.upgrade

import engine.EngineState
import engine.GameEngine
import engine.entity.weapon.weapon.Gun

class DoubleProjectileUpgrade() : Upgrade("Double weapons projectiles") {
    override fun onClick() {
        GameEngine.game?.hero?.weapons?.forEach {
            if (it is Gun) it.projectilesPerShot *= 2
        }
        GameEngine.state = EngineState.PLAY
    }
}