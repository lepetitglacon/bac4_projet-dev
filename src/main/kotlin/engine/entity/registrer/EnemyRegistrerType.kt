package engine.entity.registrer

import engine.entity.mob.enemy.EnemyType
import engine.entity.sprite.Sprite

class EnemyRegistrerType(
    val name: String,
    val type: EnemyType,

    val startingWave: Int,
    val maxHp: Int,
    val hp: Int = maxHp,
    val speed: Double,

    val xpDropRate: Double,
    val xp: Int,

    val width: Int,
    val height: Int
) {

}