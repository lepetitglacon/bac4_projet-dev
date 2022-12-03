package engine.sound

import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

data class AudioPlayer(
    val name: String,
    val filepath: String,
    val clip: Clip = AudioSystem.getClip(),
    val ais: AudioInputStream = AudioSystem.getAudioInputStream(File("assets/sound/$filepath.wav"))
    )