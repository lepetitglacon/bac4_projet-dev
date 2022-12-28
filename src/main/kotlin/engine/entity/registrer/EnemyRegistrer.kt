package engine.entity.registrer

class EnemyRegistrer {
    val enemies = mutableListOf<EnemyType>()

    fun add(enemyType: EnemyType) {
        enemies.add(enemyType)
    }
}