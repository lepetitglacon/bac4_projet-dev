package engine.resource

import Logger
import engine.GameEngine
import engine.game.Game
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.io.StringReader
import java.io.StringWriter
import java.net.URL
import java.util.*
import javax.imageio.ImageIO


object ResourceManager {
    val logger = System.getLogger("truc")
    val assetsUrls = mutableListOf<String>(
        "assets/",
        "assets/img/",
        "assets/img/objects/",
        "assets/img/items/",
        "assets/img/weapons/",
    )
    val filesUrls = mutableListOf<String>(
        "properties/",
        "properties/weapon/",
        "properties/mob/",
    )


    private fun getUrl(name: String, ext: String): URL? {
        val r = javaClass.classLoader.getResource("$name.$ext")
        Logger.log("tried $name.$ext")
        return r
    }

    /**
     * used to get sprites and spritesheets
     */
    fun getSpriteSheet(name: String): BufferedImage {
        var img: BufferedImage? = null
        var found: String = ""

        for (url in assetsUrls) {
            try {
                img = ImageIO.read(getPng("$url$name"))
                found = url
            }
            catch (e: IOException) {}
            catch (e: IllegalArgumentException) {}
        }
        if (img != null) {
            Logger.log("Spritesheet loaded : $name, found in $found")
            return img;
        }
        Logger.error("failed to load spritesheet $name, searched in $assetsUrls")
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

    /**
     * used to get audio content
     */
    fun getProperties(name: String): URL {
        return getUrl(name, "properties")!!
    }

    /**
     * used to get sprites and spritesheets
     */
    fun getFile(name: String, ext: String): File {
        var file: File? = null
        var found: String = ""

        for (url in filesUrls) {
            try {
                file = File(getUrl(name, ext).toString())
                found = url
            }
            catch (e: IOException) {}
            catch (e: IllegalArgumentException) {}
        }
        if (file != null) {
            Logger.log("Spritesheet loaded : $name, found in $found")
            return file;
        }
        Logger.error("failed to load spritesheet $name, searched in $assetsUrls")
        return File("")
    }

    fun saveProperties() {
        val props = Properties()
        props.setProperty("engine_behelits", GameEngine.behelits.toString())
        props.setProperty("engine_upgrades", GameEngine.upgrades.joinToString())
        val file = File("properties/engine.properties")
        props.store(file.outputStream(), "properties of engine")
    }

    fun getProperties(): Properties {
        val properties = Properties()
        val file = File("properties/engine.properties")
        properties.load(file.inputStream())
        return properties
    }
}