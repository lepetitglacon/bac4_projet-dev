package engine.resource

import engine.entity.sprite.Sprite
import java.awt.image.BufferedImage
import java.io.IOException
import java.net.URL
import javax.imageio.ImageIO

object ResourceManager {

    private fun getUrl(name: String, ext: String): URL {
        val r = javaClass.classLoader.getResource("$name.$ext")
//        Logger.asset(name, r!!.toString())
        return r
    }

    fun getSpriteSheet(name: String): BufferedImage {
        try {
            return ImageIO.read(getPng("assets/img/$name"))
        } catch (e: IOException) {
//            Logger.info(e.message ?: "no message")
        }
        return BufferedImage(0,0,0)
    }

    fun getPng(name: String): URL {
        return getUrl(name, "png")
    }

    fun getWav(name: String): URL {
        return getUrl(name, "wav")
    }
}