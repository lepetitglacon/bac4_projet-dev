package engine.events

import engine.Renderer
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

class JFrameResizeListener() : ComponentAdapter() {
    override fun componentResized(e: ComponentEvent) {
//        println("w: ${e.component.size.width} h: ${e.component.size.height}")
        Renderer.WINDOW_WIDTH = e.component.size.width
        Renderer.WINDOW_HEIGHT = e.component.size.height
        Renderer.repaint()
    }
}