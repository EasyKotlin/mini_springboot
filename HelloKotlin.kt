fun helloKotlin():String {
    val words = mutableListOf<String>()
    words.add("Hello")
    words.add("Kotlin!")
    words.add(java.util.Date().toString())
    return words.joinToString(separator=" ")
}

fun main(args:Array<String>){
    println(helloKotlin())
}
