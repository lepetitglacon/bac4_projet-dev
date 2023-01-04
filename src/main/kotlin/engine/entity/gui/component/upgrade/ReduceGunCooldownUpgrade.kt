package engine.entity.gui.component.upgrade

import engine.EngineState
import engine.GameEngine
import engine.entity.weapon.weapon.Gun

class ReduceGunCooldownUpgrade() : Upgrade("Reduce gun cooldown") {
    override fun onClick() {
        GameEngine.game?.hero?.weapons?.forEach {
            if (it is Gun) it.cooldown -= 100
        }
        GameEngine.state = EngineState.PLAY
    }
}