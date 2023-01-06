package engine.entity.`object`

import engine.GameEngine
import engine.entity.sprite.Sprite
import engine.resource.SpriteFactory
import java.awt.Graphics2D

class Soul : Object() {
    override var speed: kotlin.Double = 0.0
    override var sprite: Sprite = SpriteFactory.get("object_mark")

    var xp: Int = 0

    override fun xFromHero(): Int {
        return x - width/2 - GameEngine.game?.hero?.x!! + GameEngine.window.WIDTH / 2
    }

    override fun yFromHero(): Int {
        return y - height/2 - GameEngine.game?.hero?.y!! + GameEngine.window.HEIGHT / 2
    }

    override fun move() {

    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            g.fillOval(xFromHero(), yFromHero(), width, height)
        }
        g.drawImage(sprite.image, null, xFromHero(), yFromHero())
    }
}