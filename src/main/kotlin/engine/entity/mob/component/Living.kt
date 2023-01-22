package engine.entity.mob.component

interface Living {
    var hp: Double
    var maxHp: Double

    fun applyDamage(damage: Int) { hp -= damage }
    fun heal(heal: Int) { hp += heal }
}