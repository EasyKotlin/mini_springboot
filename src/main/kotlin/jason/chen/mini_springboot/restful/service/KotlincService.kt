package jason.chen.mini_springboot.restful.service

import jason.chen.mini_springboot.restful.config.KotlinBin
import org.springframework.stereotype.Service
import java.io.File

/**
 * Created by jack on 2017/5/31.
 */

@Service
class KotlincService {

    fun kotlinc(ktFile: String) {
        val file = File(".")
        file.listFiles().forEach(::println)
        val kotlinc = KotlinBin.KOTLINC.binPath + " " + ktFile
        println(kotlinc)
        val runtime: Runtime = Runtime.getRuntime()
        val process: Process = runtime.exec(kotlinc)
        val exitValue = process.waitFor()
        if (exitValue != 0) {
            println("exit with $exitValue")
            return
        }

        process.inputStream.bufferedReader().lines().forEach {
            println(it)
        }
    }

    fun kotlin(ktFile: String): Array<String> {
        var result = arrayOf<String>()

        kotlinc(ktFile)

        val ktClass = " " + ktFile.substring(0, ktFile.indexOf(".kt")) + "Kt"
        println(ktClass)
        val kotlin = KotlinBin.KOTLIN.binPath + ktClass
        println(kotlin)
        val runtime: Runtime = Runtime.getRuntime()
        val process: Process = runtime.exec(kotlin)
        val exitValue = process.waitFor()
        if (exitValue != 0) {
            println("exit with $exitValue")
            result.plus("exit with $exitValue")
            return result
        }

        process.inputStream.bufferedReader().lines().forEach {
            println(it)
            result.plus(it)
        }
        return result
    }


    fun testThread() {
        val startHookThread = Thread({
            println("Hello, I am startHookThread")
        })

        startHookThread.start()
    }
}
