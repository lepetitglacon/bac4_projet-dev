package engine

import engine.entities.Entity
import engine.enums.GameEngineDrawingType
import engine.events.JFrameResizeListener
import engine.events.UserInputListener
import engine.gui.Gui
import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.Timer

object GameEngine {
    const val FRAME_PER_SECOND = 60
    val drawingType = GameEngineDrawingType.ABSOLUTE

    val gui = Gui()

    val ticks = 0
    val entities = mutableListOf<Entity>()

    fun start() {
        SwingUtilities.invokeLater {
            val f = JFrame()
            with(f) {
                defaultCloseOperation = JFrame.EXIT_ON_CLOSE
                title = "Bac+4 survival game - Esteban GAGNEUR"
                isResizable = true
                add(gui, BorderLayout.CENTER)
                pack()
                setLocationRelativeTo(null)
                isVisible = true
            }

            // Set up key event handler
            f.addKeyListener(UserInputListener())
            f.addComponentListener(JFrameResizeListener())

            gui.timer.start()
        }
    }

    fun addComponent(e: Entity) {
        entities.add(e)
    }
}