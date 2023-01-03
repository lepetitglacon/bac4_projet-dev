package engine.entity.mob.component

interface Leveling {
    var level: Int
    var xp: Int
    var maxXp: Int

    fun levelUp(scalar: Int) {
        xp = 0
        maxXp *= scalar
    }
}