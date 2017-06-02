package jason.chen.mini_springboot.restful.service

import jason.chen.mini_springboot.restful.config.KotlinBin
import org.springframework.stereotype.Service
import java.io.File

/**
 * Created by jack on 2017/5/31.
 */

@Service
class KotlinFilesService {

    fun listKotlinFileNames(): List<String> {
        val ktFilePath = KotlinBin.KOTLINC.destPath
        val file = File(ktFilePath)
        val fileList = file.listFiles()
        val filenames = mutableListOf<String>()
        fileList
                .filter { it.path.endsWith(".kt") }
                .forEach {
                    println(it.path)
                    filenames.add(it.path)
                }
        return filenames
    }

    fun listKotlinFiles(): List<File> {
        val ktFilePath = KotlinBin.KOTLINC.destPath
        val file = File(ktFilePath)
        val fileList = file.listFiles()
        fileList.forEach(::println)
        val filterList = fileList.filter { it.path.endsWith(".kt") }
        println(filterList)
        return filterList
    }


    fun showKotlinFileContent(filename: String): String {
        val ktFilePath = KotlinBin.KOTLINC.destPath
        val file = File(ktFilePath + "/" + filename)
        val content = file.readText(Charsets.UTF_8)
        println(content)
        return content
    }

}
