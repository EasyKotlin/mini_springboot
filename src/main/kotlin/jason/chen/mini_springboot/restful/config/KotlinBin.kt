package jason.chen.mini_springboot.restful.config

/**
 * Created by jack on 2017/6/1.
 */
enum class KotlinBin(val binPath: String, val destPath:String) {
    KOTLINC("src/main/resources/kotlinc/bin/kotlinc","ktfiles"),
    KOTLIN("src/main/resources/kotlinc/bin/kotlin","ktfiles")
}
