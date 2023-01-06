package engine.resource

import java.awt.image.BufferedImage
import java.io.IOException
import java.lang.IllegalArgumentException
import java.net.URL
import javax.imageio.ImageIO

object ResourceManager {
    val logger = System.getLogger("truc")
    val urls = mutableListOf<String>(
        "assets/",
        "assets/img/",
        "assets/img/objects/",
        "assets/img/items/",
        "assets/img/weapons/",
    )

    private fun getUrl(name: String, ext: String): URL? {
        val r = javaClass.classLoader.getResource("$name.$ext")
            logger.log(System.Logger.Level.TRACE, "tried")
//        Logger.asset(name, r!!.toString())
        return r
    }

    /**
     * used to get sprites and spritesheets
     */
    fun getSpriteSheet(name: String): BufferedImage {
        var img: BufferedImage? = null
        var found: String = ""

        for (url in urls) {
            try {
                img = ImageIO.read(getPng("$url$name"))
                found = url
            }
            catch (e: IOException) {}
            catch (e: IllegalArgumentException) {}
        }
        if (img != null) {
            logger.log(System.Logger.Level.INFO, "Spritesheet loaded : $name, found in $found")
            return img;
        }
        logger.log(System.Logger.Level.ERROR, "failed to load spritesheet $name, searched in $urls")
        return BufferedImage(0, 0, 0)
    }

    /**
     * used to get png (not sprite/spritesheet)
     */
    fun getPng(name: String): URL? {
        return getUrl(name, "png")
    }

    /**
     * used to get audio content
     */
    fun getWav(name: String): URL? {
        return getUrl(name, "wav")
    }
}