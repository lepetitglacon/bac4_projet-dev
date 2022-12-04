package engine.sound

import engine.ResourceManager
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

data class AudioPlayer(
    val name: String,
    val filepath: String,
    val clip: Clip = AudioSystem.getClip(),
    val ais: AudioInputStream = AudioSystem.getAudioInputStream(ResourceManager.getWav(filepath))
    )