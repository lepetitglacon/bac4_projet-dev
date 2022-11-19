import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import java.awt.Color
import kotlin.random.Random

const val ENTITY_RAYON = 25
const val ENTITY_SPEED = 10
const val ENNEMY_PER_WAVE = 6

enum class EntityState {
    IDLE
}


object EntityFactory {
    fun createHero(): Hero {
        return Hero()
    }

    fun createRandomEnemy(): Enemy {
        val enemy = Enemy()
        enemy.position.x = Random.nextDouble(0.0, WINDOW_WIDTH.toDouble())
        enemy.position.y = Random.nextDouble(0.0, WINDOW_HEIGHT.toDouble())

        enemy.position = Vector2(enemy.position.x + ENTITY_RAYON/2, enemy.position.y + ENTITY_RAYON/2)

        enemy.color = Color.BLACK

        return enemy
    }

    /**
     * Add new ennemies to Renderer entity array
     */
    fun createEnemiesForWave(wave: Int) {
        val number = ENNEMY_PER_WAVE * wave
        for (i in 0..number) {
            Renderer.entities.add(createRandomEnemy())
        }
    }
}