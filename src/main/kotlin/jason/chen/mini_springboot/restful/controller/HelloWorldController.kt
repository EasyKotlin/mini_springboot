package jason.chen.mini_springboot.restful.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jack on 2017/6/2.
 */
@RestController
class HelloWorldController{
    @GetMapping("/hello")
    fun helloWorld(): String {
        val words = mutableListOf<String>()
        words.add("Hello")
        words.add("World!")
        words.add(java.util.Date().toString())

        return words.joinToString(separator = " ")
    }
}
