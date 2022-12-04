package engine.entity.interfaces

interface Levelable {
    var xp: Int
    var xpToNextLevel: Int
    var level: Int

    fun checkForLevelUp()
}