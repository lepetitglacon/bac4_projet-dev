package engine.entity.mob.component

interface Attacking {
    val damages: Int

    fun attack() : Int
}