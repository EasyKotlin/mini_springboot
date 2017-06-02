package jason.chen.mini_springboot.restful.controller

import jason.chen.mini_springboot.restful.service.KotlincService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jack on 2017/5/31.
 */
@RestController
class KotlincController(val kotlincService: KotlincService) {

    @GetMapping("/kotlinc")
    fun kotlinc(@RequestParam(value="ktFile") ktFile:String):String {
        kotlincService.kotlinc(ktFile)
        return ktFile
    }

    @GetMapping("/kotlin")
    fun kotlin(@RequestParam(value="ktFile") ktFile:String): MutableList<String> {
        return kotlincService.kotlin(ktFile)
    }



}
