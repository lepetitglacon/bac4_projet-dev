package engine.sound

import javax.sound.sampled.FloatControl

object SoundManager {
    val clips: MutableMap<String, AudioPlayer> = mutableMapOf()

    fun registerAudioFile(name: String, filepath: String) {
        clips[name] = AudioPlayer(name, filepath)
        clips[name]!!.clip.open(clips[name]!!.ais)
    }

    fun loadFiles() {
        registerAudioFile("main song", "assets/sound/main")
        registerAudioFile("death", "assets/sound/death")
        registerAudioFile("xp", "assets/sound/xp/xp")
        registerAudioFile("level up", "assets/sound/xp/levelup")
        registerAudioFile("fire", "assets/sound/fire")
        registerAudioFile("explosion", "assets/sound/explosion")
        registerAudioFile("hit", "assets/sound/hit")
    }

    fun play(name: String) {
        Thread {
            if (clips.containsKey(name)) {
                val ap = clips[name]!!
                ap.clip.microsecondPosition = 0
                ap.clip.start()
            } else {
                Logger.warning("\"$name\" does not exist in audio files (not loaded)")
            }

        }.start()
    }

    fun stop(name: String) {
        if (clips.containsKey(name)) {
            clips[name]!!.clip.stop()
        } else {
            Logger.warning("\"$name\" does not exist in audio files (not loaded)")
        }
    }

    fun changeVolume(name: String, volume: Float) {
        if (clips.containsKey(name)) {
            clips[name]!!.clip
            val gainControl = clips[name]!!.clip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
            gainControl.value = volume
        } else {
            Logger.warning("\"$name\" does not exist in audio files (not loaded)")
        }
    }
}