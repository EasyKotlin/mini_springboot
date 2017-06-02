package jason.chen.mini_springboot.restful.controller

import jason.chen.mini_springboot.restful.dynamic.MyJavaCompiler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by jack on 2017/6/2.
 */


@Controller
class MyJavaCompilerController(val myJavaCompiler: MyJavaCompiler) {
    @GetMapping(value = "/myJavaCompiler")
    @ResponseBody
    fun myJavaCompiler(@RequestParam(value = "classFile") classFile: String) {
//        myJavaCompiler.compileClass(classFile, null)
        myJavaCompiler.compileJavaSource(classFile,null)
    }
}
