package jason.chen.mini_springboot.restful.langbasic.functions

/**
 * Created by mvasic on 7/20/16.
 */
fun <T> genericFunctionsExample(x: T){
    println("Value: $x")
}

fun tryGenericFunctionsExampe(){
    genericFunctionsExample(5)
    genericFunctionsExample("Some word!")
    genericFunctionsExample('c')
    genericFunctionsExample(5.55)
}
