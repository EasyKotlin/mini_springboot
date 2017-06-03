package jason.chen.mini_springboot.restful.langbasic.functions

/**
 * Created by mvasic on 7/30/16.
 */
fun higherOrderExample(execute: () -> String): String {
    return execute.invoke()
}

fun printString(): String {
    return "Some string..."
}

fun useHigherOrderExample() {
    val string = higherOrderExample(::printString)
    println(string)
}

fun useHigherOrderExampleLambda() {
    val string = higherOrderExample { "Yet some other string" }
    println(string)
}
