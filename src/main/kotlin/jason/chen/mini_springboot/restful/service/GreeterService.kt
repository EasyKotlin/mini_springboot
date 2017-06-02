package jason.chen.mini_springboot.restful.service

import com.squareup.kotlinpoet.*
import org.springframework.stereotype.Service

/**
 * Created by jack on 2017/6/3.
 */

@Service
class GreeterService {

    fun generateGreeter() :String{
        val greeterClass = ClassName.get("", "Greeter")
        val kotlinFile = KotlinFile.builder("", "HelloWorld")
                .addType(TypeSpec.classBuilder("Greeter")
                        .primaryConstructor(FunSpec.constructorBuilder()
                                .addParameter("name", String::class)
                                .build())
                        .addProperty(PropertySpec.builder("name", String::class)
                                .initializer("name")
                                .build())
                        .addFun(FunSpec.builder("greet")
                                .addStatement("println(%S)", "Hello, \$name")
                                .build())
                        .build())
                .addFun(FunSpec.builder("main")
                        .addParameter("args", TypeName.get(Array<String>::class))
                        .addStatement("%T(args[0]).greet()", greeterClass)
                        .build())
                .build()

        kotlinFile.writeTo(System.out)
        return kotlinFile.toString()
    }
}
