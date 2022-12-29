package engine.entity.weapon

import engine.entity.Entity
import engine.entity.sprite.Sprite

abstract class Weapon : Entity()
{
    abstract val effect: WeaponEffect
}