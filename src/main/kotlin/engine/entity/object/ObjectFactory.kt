package engine.entity.`object`

import engine.GameEngine
import engine.entity.mob.enemy.Enemy

object ObjectFactory {

    fun createSoul(e: Enemy) {
        val s = Soul()
        s.width = 32
        s.height = 32
        s.pos.x = e.center().x - s.width/2
        s.pos.y = e.center().y - s.height/2
        s.pos.x = e.pos.x
        s.pos.y = e.pos.y
        s.xp = e.xpToGive
        s.setDrawingPosition()
        GameEngine.game?.objects?.add(s)
    }

    fun createBehelit(e: Enemy) {
        val s = Behelit()
        s.width = 32
        s.height = 32
        s.pos.x = e.center().x - s.width/2
        s.pos.y = e.center().y - s.height/2
        s.pos.x = e.pos.x
        s.pos.y = e.pos.y
        s.setDrawingPosition()
        GameEngine.game?.objects?.add(s)
    }
}