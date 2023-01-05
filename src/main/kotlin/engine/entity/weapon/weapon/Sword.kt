package engine.entity.weapon.weapon

import engine.entity.sprite.Sprite
import engine.entity.weapon.Weapon
import engine.resource.SpriteFactory
import java.awt.Graphics2D

class Sword : Weapon() {
    override var sprite: Sprite = SpriteFactory.get("weapon_item_sword")
    override var cooldown: Long = 2000

    override fun update() {
    }

    override fun draw(g: Graphics2D) {

    }
}