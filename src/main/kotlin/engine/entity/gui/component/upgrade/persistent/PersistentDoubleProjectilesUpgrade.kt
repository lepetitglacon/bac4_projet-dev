package engine.entity.gui.component.upgrade.persistent

import engine.GameEngine
import engine.entity.weapon.weapon.GrenadeLauncher
import engine.entity.weapon.weapon.Gun

class PersistentDoubleProjectilesUpgrade : PersistentUpgrade("double weapons projectiles") {
    override val maxLevel: Int = 5
    override val cost: Int = 2


    override fun bonus() {
        GameEngine.game?.hero?.weapons?.forEach {
            it.projectilesPerShot *= 2
        }
    }

}