package engine.entity.gui.component

import engine.entity.Entity
import engine.entity.gui.Gui
import engine.entity.sprite.Sprite
import java.awt.Color
import java.awt.Graphics2D

class WindowComponent : GuiComponent() {
    override var gui: Gui? = null
    override var parent: GuiComponent? = null

    override fun draw(g: Graphics2D) {
        g.color = Color(0, 0, 0, 150)
        g.fillRect(x, y, width, height)
        g.color = Color.WHITE
        g.drawLine(x, y, x + width, y)
        g.drawLine(x, y + height, x + width, y + height)
    }

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
        TODO("Not yet implemented")
    }

    override fun yFromHero(): Int {
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
}