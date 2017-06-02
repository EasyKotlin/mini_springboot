package jason.chen.mini_springboot.restful.controller

import jason.chen.mini_springboot.restful.service.PrimaryTypeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jack on 2017/6/3.
 */

@RestController
class PrimaryTypeController(val PrimaryTypeService: PrimaryTypeService) {

    @GetMapping("/getKotlinPrimaryType")
    fun getKotlinPrimaryType(): MutableList<Any> {
        return PrimaryTypeService.getKotlinPrimaryType()
    }

}
