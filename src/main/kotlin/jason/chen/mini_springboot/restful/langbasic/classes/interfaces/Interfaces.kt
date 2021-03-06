package jason.chen.mini_springboot.restful.langbasic.classes.interfaces

/**
 * Created by milosvasic on 6/9/16.
 */
interface Audio {
    fun volumeUp();
    fun volumeDown();
    fun setVolume(volume: Int)
}

interface Video {
    val brightness: Int

    val contrast: Int
        get() = 100

    fun play() {
        println("Play")
    }

    fun pause()
}

open class MultimediaDevice : Audio {
    override fun volumeUp() {
        println("Volume up")
    }

    override fun volumeDown() {
        println("Volume down")
    }

    override fun setVolume(volume: Int) {
        println("Set volume to $volume")
    }
}

class DvdPlayer : MultimediaDevice(), Video {
    override val brightness: Int = 100

    override fun pause() {
        println("Pause")
    }

}
