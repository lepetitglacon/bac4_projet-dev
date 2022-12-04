package engine

import engine.logger.Logger
import java.net.URL
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

object ResourceManager {

    private fun getUrl(name: String, ext: String): URL {
        val r = javaClass.classLoader.getResource("$name.$ext")
        Logger.asset(name, r!!.toString())
        return r
    }

    fun getPng(name: String): URL {
        return getUrl(name, "png")
    }

    fun getWav(name: String): URL {
        return getUrl(name, "wav")
    }
}