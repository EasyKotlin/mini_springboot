package jason.chen.mini_springboot.restful.controller

import jason.chen.mini_springboot.restful.service.KotlinFilesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File

/**
 * Created by jack on 2017/5/31.
 */
@RestController
class KotlinFilesController(val kotlinFilesService: KotlinFilesService) {

    @GetMapping("/listKotlinFiles")
    fun listKotlinFiles(): List<File> {
        return kotlinFilesService.listKotlinFiles()
    }

    @GetMapping("/listKotlinFileNames")
    fun listKotlinFileNames(): List<String> {
        return kotlinFilesService.listKotlinFileNames()
    }

    @GetMapping("/showKotlinFileContent")
    fun showKotlinFileContent(@RequestParam(value = "filename") filename: String): String {
        return kotlinFilesService.showKotlinFileContent(filename)
    }
}
