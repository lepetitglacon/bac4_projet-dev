package engine.entity.mob.component

import engine.EngineState
import engine.GameEngine
import kotlin.math.pow

interface Leveling {
    var level: Int
    var xp: Double
    var maxXp: Double

    fun levelUp(scalar: Double) {
        xp = 0.0
        maxXp *= 1.4
        level++
        GameEngine.state = EngineState.SHOP
    }
}