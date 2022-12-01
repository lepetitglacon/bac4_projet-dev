package engine.entity.interfaces

interface Levelable {
    val xp: Int
    val xpToNextLevel: Int

    fun nextLevel()
}