
《Kotlin 编程思想》（Thinking in Kotlin ）
===


Kotlin代码编译过程
===

## 命令行执行过程

我们通过kotlinc的shell代码，可以看出kotlin的源码编译、执行是通过java命令（也就是JVM执行引擎）完成的。

其中，编译是通过

```shell
$ java -cp $KOTLIN_HOME/lib/kotlin-preloader.jar org.jetbrains.kotlin.preloading.Preloader -cp $KOTLIN_HOME/lib/kotlin-compiler.jar org.jetbrains.kotlin.cli.jvm.K2JVMCompiler HelloWorld.kt

```

然后，我们通过测试发现，Kotlin的class文件是无法直接通过java执行的。看了kotlin的shell，我们可以发现，Kt.class字节码的运行是通过如下命令执行的：

```shell
$ java -Dkotlin.home=$KOTLIN_HOME -cp $KOTLIN_HOME/lib/kotlin-runner.jar org.jetbrains.kotlin.runner.Main HelloWorldKt
Hello,World!

```

多了一层kotlin-runner.jar的映射逻辑。



其中测试代码如下：


```

//HelloKotlin.kt

fun main(args:Array<String>){
    val date = java.util.Date()
    println("Hello,Kotlin! " + date)
}


//HelloWorld.kt
fun main(args: Array<String>){
    println("Hello,World!")
}

```




## RESTFule kotlinc

NullCheck.kt

```kotlin


/**
 * Created by jack on 2017/5/30.
 */

fun main(args: Array<String>) {
    val s: String? = null
    println(s?.length)

    var fooNullable: String? = "abc"
    println(fooNullable?.length) // => 3
    println(fooNullable?.length ?: -1) // => 3
    fooNullable = null
    println(fooNullable?.length) // => null
    println(fooNullable?.length ?: -1) // => -1

    testNullSafeOperator(null)
    testNullSafeOperator("12345678901")
    testNullSafeOperator("123")

}


fun testNullSafeOperator(string: String?) {
    println(string?.toCharArray()?.getOrNull(10)?.hashCode())
}

fun toString(any:Any): String{
  return any?.toString()
}

```

> http://localhost:9891/kotlin?ktFile=NullCheck.kt

```json
[
"null",
"3",
"3",
"null",
"-1",
"null",
"49",
"null"
]
```


> http://localhost:9891/showKotlinFileContent?filename=LambdaExpression.kt

```kotlin
// LambdaExpression.kt
// src/main/resources/kotlinc/bin/kotlin ./LambdaExpressionKt


/**
 * Created by jack on 2017/5/30.
 */

//    Lambda 表达式
val fsum0 = { x: Int, y: Int ->  x + y  }
val fsum1 = { x: Int, y: Int -> { x + y } }
val fsum2 = fun(x: Int, y: Int): Int = x + y
val fsum3 = fun Int.(other: Int): Int = this + other


fun main(args: Array<String>) {
    println(fsum0(1, 1))
    println(fsum0.invoke(1,1))

    println(fsum1(1, 1).invoke())
    println(fsum2(1, 1))
    println(1.fsum3(1))

    val ints = intArrayOf(-1, -2, 3, 4, 5, 6)
    var sum = 0
    ints.filter { it > 0 }.forEach {
        sum = it.fsum3(sum)
        //sum += it;
    }
    println(sum)//18

    val doubled = ints.map { value -> value * 2 }
    println(doubled)


}

```




> http://localhost:9891/listKotlinFileNames

```json
[
"ktfiles/BasicSyntax.kt",
"ktfiles/CallableReferences.kt",
"ktfiles/DataClass.kt",
"ktfiles/Functions.kt",
"ktfiles/HelloKotlin.kt",
"ktfiles/HelloWorld.kt",
"ktfiles/HelloWorldOop.kt",
"ktfiles/InterfacesInKotlin.kt",
"ktfiles/KotlinClasses.kt",
"ktfiles/LambdaExpression.kt",
"ktfiles/NestedClass.kt",
"ktfiles/NullCheck.kt",
"ktfiles/Primitives.kt",
"ktfiles/TypeSystem.kt"
]
```


## Kotlin 文件操作

```kotlin
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

```


## Kotlin 执行Shell

```kotlin

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


```


## Spring MVC Controller Kotlin的写法

```kotlin
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


```



















# Spring Boot Kotlin JPA Mysql Restful API Demo project

This is the source code for the a sample Spring Boot application developed with Kotlin and Spring Data JPA. 

You can launch the application with by running:

		$ ./gradlew bootRun

This project uses `kotlin-spring` plugin to avoid requiring `open` modifier on proxified
classes and methods, see [this blog post](https://blog.jetbrains.com/kotlin/2016/12/kotlin-1-0-6-is-here/) for more details.

Make sure you have at least IntelliJ IDEA 2016.3 and Kotlin plugin 1.0.6 since `kotlin-spring` and
`kotlin-jpa` compiler plugins require this version.

This project uses a [Kotlin based Gradle](https://blog.gradle.org/kotlin-meets-gradle) configuration,
but `build.gradle.kts` auto-complete is currently not available since Kotlin `1.1-M04` IDEA
plugin needed for that does not allow to use `kotlin-spring` and `kotlin-jpa` compiler in a reliable way.

This project is Apache 2.0 licensed.


#Springboot极简教程
http://www.jianshu.com/c/c3fe8e7aeb09



#Kotlin极简教程
http://www.jianshu.com/c/498ebcfd27ad
