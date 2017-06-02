package jason.chen.mini_springboot.restful.controller

import jason.chen.mini_springboot.restful.service.GreeterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jack on 2017/6/3.
 */

@RestController
class GreeterController {
    @Autowired val greeterService: GreeterService? = null

    @GetMapping("/greeter")
    fun greeter(): String {
        return greeterService!!.generateGreeter()
    }


}
