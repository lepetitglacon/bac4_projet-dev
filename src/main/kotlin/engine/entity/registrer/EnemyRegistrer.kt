package engine.entity.registrer

class EnemyRegistrer {
    val enemies = mutableListOf<EnemyRegistrerType>()

    fun add(enemyType: EnemyRegistrerType) {
        enemies.add(enemyType)
    }
}