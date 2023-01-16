package engine.entity.gui.component.upgrade.weapon.grenadelauncher

import engine.EngineState
import engine.GameEngine
import engine.entity.gui.component.upgrade.Upgrade
import engine.entity.weapon.weapon.GrenadeLauncher
import engine.entity.weapon.weapon.Gun

class GrenadeLauncherWeaponUpgrade() : Upgrade("Gain a grenade launcher") {
    override val maxLevel: Int = 1

    override fun onClick() {
        GameEngine.game?.hero?.weapons?.add(GrenadeLauncher())
        GameEngine.state = EngineState.PLAY
    }
}