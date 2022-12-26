package engine.entity.gui.component

import engine.entity.Entity
import engine.entity.gui.Gui
import engine.entity.sprite.Sprite
import java.awt.Color
import java.awt.Graphics2D

class StringComponent : GuiComponent() {
    var string = ""
    var centered = false

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

    override fun xFromHero() {
        TODO("Not yet implemented")
    }

    override fun yFromHero() {
        TODO("Not yet implemented")
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
        g.color = Color.black
        var drawX = x
        var drawY = y

        if (parent != null)
        {
            drawX = parent!!.x
            drawY = (parent!!.y + g.fontMetrics.getStringBounds(string, g).height).toInt()
        }
        if (centered)
        {
            drawX = ((g.fontMetrics.getStringBounds(string, g).width / 2) + drawX).toInt()
            drawY = ((g.fontMetrics.getStringBounds(string, g).height / 2) + drawY).toInt()
        }
        g.drawString(string, drawX, drawY)
    }


}