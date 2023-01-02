package engine.entity.mob.component

interface Living {
    var hp: Int
    var maxHp: Int

    fun applyDamage(damage: Int) { hp -= damage }
    fun heal(heal: Int) { hp += heal }
}