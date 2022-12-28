package engine.entity.gui.component

import engine.GameEngine
import engine.entity.Entity
import engine.entity.gui.Gui
import engine.entity.sprite.Sprite
import java.awt.Color
import java.awt.Graphics2D

class BarComponent : GuiComponent()
{
    val PADDING = 2
    var static = false

    var filled: Int = 100
    var maxFilled: Int = 100
    var color: Color = Color(44, 248, 11)
    var completingColor: Color = Color(122, 48, 7)
    var backgroundColor: Color = Color(217,217,217, 230)

    init {
        width = 100
        height = 8
    }

    fun getFilledWidth(): Int {
        val centpourcent = width - 2 * PADDING
        return centpourcent * filled / maxFilled
    }

    fun getNonFilledWidth(): Int {
        return  width - getFilledWidth()
    }

    override var gui: Gui? = null
    override var parent: GuiComponent? = null

    override fun drawAbsolute() {
        TODO("Not yet implemented")
    }

    override fun drawRelativeToParent() {
        TODO("Not yet implemented")
    }

    override var speed: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var sprite: Sprite
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun xFromHero(): Int {
        return x - width/2 - GameEngine.game?.hero?.x!! + GameEngine.window.WIDTH / 2
    }

    override fun yFromHero(): Int {
        return y - height/2 - GameEngine.game?.hero?.y!! + GameEngine.window.HEIGHT / 2
    }

    override fun collides(entity: Entity) {
        TODO("Not yet implemented")
    }

    override fun move() {
        TODO("Not yet implemented")
    }

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun draw(g: Graphics2D) {
        var drawX: Int
        var drawY: Int

        if (static)
        {
            drawX = x
            drawY = y
        } else
        {
            drawX = xFromHero()
            drawY = yFromHero()
        }

        if (parent != null)
        {
            drawX = parent!!.x + x
            drawY = parent!!.y + y
        }

        g.color = backgroundColor
        g.fillRect(drawX, drawY, width, height)

        g.color = color
        g.fillRect(drawX + PADDING, drawY + PADDING, getFilledWidth(), height - 2*PADDING)

        g.color = completingColor
        g.fillRect(drawX + getFilledWidth(), drawY + PADDING, getNonFilledWidth() - PADDING, height - 2*PADDING)
    }
}