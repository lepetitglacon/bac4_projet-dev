package engine.entity.gui.component.upgrade.persistent

import engine.GameEngine
import engine.entity.gui.component.upgrade.Upgrade

abstract class PersistentUpgrade(string: String) : Upgrade(string) {
    abstract val cost: Int

    abstract fun bonus()

    override fun onClick() {
        if (GameEngine.behelits >= cost) {
            GameEngine.upgrades.add(this)
            GameEngine.behelits -= cost
        } else {
            GameEngine.mainMenuShop.error = "Don't have enough behelits"
        }
    }
}