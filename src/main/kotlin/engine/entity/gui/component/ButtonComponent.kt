package engine.entity.gui.component

import engine.entity.Entity
import engine.entity.gui.Gui
import engine.entity.sprite.Sprite
import java.awt.Color
import java.awt.Graphics2D

class ButtonComponent : GuiComponent() {
    var backgroundColor: Color = Color(155, 155,155, 150)
    var string: StringComponent = StringComponent()

    override var gui: Gui? = null
    override var parent: GuiComponent? = null

    override fun drawAbsolute() {
        TODO("Not yet implemented")
    }

    override fun drawRelativeToParent() {
        TODO("Not yet implemented")
    }

    override var speed: Int = 0
    override var sprite: Sprite = Sprite()

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

    override fun draw(g: Graphics2D) {
        // background
        g.color = backgroundColor
        g.fillRect(x, y, width, height)

        string.draw(g)
    }


}