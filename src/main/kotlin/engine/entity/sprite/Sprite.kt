package engine.entity.sprite

import java.awt.image.BufferedImage

open class Sprite(open var image: BufferedImage? = null)
{
    open fun get(): BufferedImage? {
        return image
    }
}