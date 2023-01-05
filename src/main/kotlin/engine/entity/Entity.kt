package engine.entity

import engine.GameEngine
import engine.entity.sprite.Sprite
import engine.math.MathUtility
import engine.math.Vec2
import java.awt.Graphics2D
import java.awt.Rectangle
import kotlin.math.roundToInt


abstract class Entity : Rectangle()
{
    var pos: Vec2 = Vec2()
    abstract var speed: kotlin.Double
    abstract var sprite: Sprite

    open fun xFromHero(): Int = x - width/2 - GameEngine.game?.hero?.x!! + GameEngine.window.WIDTH / 2
    open fun yFromHero(): Int = y - height/2 - GameEngine.game?.hero?.y!! + GameEngine.window.HEIGHT / 2
    fun collides(entity: Entity) : Boolean = MathUtility.collides(this, entity)
    abstract fun move()
    open fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            g.drawString("pos ${pos.x.toInt()} ${pos.y.toInt()}", xFromHero(), yFromHero() + 10)
            g.drawString("cen ${center().x.toInt()} ${center().y.toInt()}", xFromHero(), yFromHero() + 20)
            g.drawOval(xFromHero(), yFromHero(), width, height)
        }
    }

    open fun update() { setDrawingPosition() }
    fun setDrawingPosition() {
        x = pos.x.toInt()
        y = pos.y.toInt()
    }

    /**
     * return the center of the entity
     */
     fun center(): Vec2 = Vec2(pos.x + width / 2, pos.y + height / 2)
}