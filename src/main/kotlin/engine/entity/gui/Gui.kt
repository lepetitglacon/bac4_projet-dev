package engine.entity.gui

import engine.entity.gui.component.GuiComponent
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

abstract class Gui : KeyListener
{
    val components: MutableList<GuiComponent> = mutableListOf()

    fun drawComponents()
    {
        components.forEach { it.draw() }
    }

    abstract fun update()

    fun draw(g: Graphics2D)
    {
        drawComponents()
    }

    override fun keyTyped(e: KeyEvent?) {}

    override fun keyPressed(e: KeyEvent?) {
        println("key pressed")
    }

    override fun keyReleased(e: KeyEvent?) {
        TODO("Not yet implemented")
    }

}