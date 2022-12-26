package engine.entity.gui

import engine.entity.gui.component.GuiComponent
import engine.event.input.InputListener
import java.awt.Graphics2D

abstract class Gui : InputListener
{
    val components: MutableList<GuiComponent> = mutableListOf()

    fun drawComponents(g: Graphics2D)
    {
        components.forEach { it.draw(g) }
    }

    abstract fun update()

    fun draw(g: Graphics2D)
    {
        drawComponents(g)
    }

}