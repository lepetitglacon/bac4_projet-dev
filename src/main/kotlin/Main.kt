import engine.GameEngine
import engine.Vector2
import engine.entities.physical.AbstractPhysicalEntity
import engine.entities.physical.Oval
import engine.factories.EntityFactory
import java.awt.Color

fun main(args: Array<String>) {
//    Renderer.initEngine()
    GameEngine.start()
    GameEngine.addComponent(Oval(Vector2(100.0, 50.0), 100, 100, Color.RED))
    val oval = Oval(Vector2(150.0, 150.0), 100, 100, Color.MAGENTA)
    GameEngine.addComponent(oval)


    println(GameEngine.entities.filterIsInstance<AbstractPhysicalEntity>().first().collides(oval))
}