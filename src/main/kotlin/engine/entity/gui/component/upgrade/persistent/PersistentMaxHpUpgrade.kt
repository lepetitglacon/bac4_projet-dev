package engine.entity.gui.component.upgrade.persistent

import engine.GameEngine

class PersistentMaxHpUpgrade : PersistentUpgrade("add 0.8 times max hp") {
    override val maxLevel: Int = 5
    override val cost: Int = 2


    override fun bonus() {
        GameEngine.game?.hero?.maxHp = GameEngine.game?.hero?.maxHp!! * .8
        GameEngine.game?.hero?.hp = GameEngine.game?.hero?.hp!! * .8
    }

}