package jason.chen.mini_springboot.restful.service

import jason.chen.mini_springboot.restful.config.KotlinBin
import org.springframework.stereotype.Service
import java.io.File

/**
 * Created by jack on 2017/5/31.
 */

@Service
class KotlincService {


    /**
     * src/main/resources/kotlinc/bin/kotlinc ktfiles/Primitives.kt -d ktfiles
     */
    fun kotlinc(ktFile: String) {
        val file = File(".")
        file.listFiles().forEach(::println)
        val kotlinc = KotlinBin.KOTLINC.binPath + " " + KotlinBin.KOTLINC.destPath + "/" + ktFile + " -d " + KotlinBin.KOTLINC.destPath
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

    /**
     * src/main/resources/kotlinc/bin/kotlin -cp ktfiles PrimitivesKt
     */
    fun kotlin(ktFile: String): MutableList<String> {
        val result = mutableListOf<String>()

        kotlinc(ktFile)

        val ktClass = " -cp " + KotlinBin.KOTLINC.destPath + " " + ktFile.substring(0, ktFile.indexOf(".kt")) + "Kt"
        println(ktClass)
        val kotlin = KotlinBin.KOTLIN.binPath + ktClass
        println(kotlin)
        val runtime: Runtime = Runtime.getRuntime()
        val process: Process = runtime.exec(kotlin)
        val exitValue = process.waitFor()
        if (exitValue != 0) {
            println("exit with $exitValue")
            result.add("exit with $exitValue")
            return result
        }

        process.inputStream.bufferedReader().lines().forEach {
            println(it)
            result.add(it)
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
