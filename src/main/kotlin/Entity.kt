import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import factories.EntityFactory
import factories.WeaponFactory
import interfaces.*
import weapons.Weapon
import java.awt.Color
import java.awt.Graphics2D

/**
 * Une entité de base
 */
abstract class Entity :
    LivingInterface,
    WeaponizableInterface,
    DamageDealerInterface,
    CollidableInterface,
    ColorableInterface
{







}