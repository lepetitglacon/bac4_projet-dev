package engine.entity.gui.component.upgrade.game

import engine.EngineState
import engine.GameEngine
import engine.entity.gui.component.upgrade.Upgrade
import engine.entity.weapon.weapon.GrenadeLauncher
import engine.entity.weapon.weapon.Gun

class ReduceGunCooldownUpgrade() : Upgrade("Reduce gun cooldown") {
    override val maxLevel: Int = 4

    override fun onClick() {
        GameEngine.game?.hero?.weapons?.forEach {
            if (it is Gun) it.cooldown -= 100
            if (it is GrenadeLauncher) it.cooldown -= 100
        }
        GameEngine.state = EngineState.PLAY
    }
}