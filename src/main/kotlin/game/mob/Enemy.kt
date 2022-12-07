package game.mob

import engine.GameEngine
import engine.entities.CollidableEntity
import engine.entities.enums.DrawablePosition
import engine.entities.interfaces.Attacker
import engine.entities.factory.SpriteFactory
import engine.entities.factory.WeaponFactory
import engine.entities.interfaces.Weaponized
import engine.entities.weapon.Weapon
import java.awt.Color
import java.awt.Graphics2D

class Enemy : CollidableEntity(), Attacker, Weaponized {


    override var damages: Int = 5
    override val weapons: MutableList<Weapon> = mutableListOf()

    init {
        width = 32
        height = 32
        drawingPositionType = DrawablePosition.RELATIVE_TO_HERO
        sprite = SpriteFactory.getPokemonSprite()
        speed = 2.0

        weapons.add(WeaponFactory.createSword())
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            drawDebugPosition(g)
            g.color = Color.RED
            g.fillOval(getDrawingPosition().x, getDrawingPosition().y, width, height)
            g.color = Color.BLUE
            g.fillOval(getDrawingPosition().x + width/2, getDrawingPosition().y + height/2, 3, 3)

            g.color = Color.black
            g.drawOval(drawingPosition.x.toInt(), drawingPosition.y.toInt(), width, height)
            g.drawString("$position", getDrawingPosition().x, getDrawingPosition().y - 30)
        }

        // sprite
        g.drawImage(sprite, null, getDrawingPosition().x, getDrawingPosition().y)
        drawHpBar(g)
    }

    override fun move() {
        setDrawingPositionFromPosition()
        moveTo(GameEngine.game.hero.position)
    }



    override fun fireAll() {
        TODO("Not yet implemented")
    }

}