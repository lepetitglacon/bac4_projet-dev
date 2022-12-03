package engine.sound

import engine.logger.Logger
import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

object SoundManager {
    val clips: MutableMap<String, AudioPlayer> = mutableMapOf()

    fun registerAudioFile(name: String, filepath: String) {
        clips[name] = AudioPlayer(name, filepath)
    }

    fun loadFiles() {
        registerAudioFile("main song", "main")
    }

    fun playSong(name: String) {
        if (clips.containsKey(name)) {
            clips[name]!!.clip.open(clips[name]!!.ais)
            clips[name]!!.clip.start()
        } else {
            Logger.warning("$name does not exist in audio files (not loaded)")
        }

    }
}