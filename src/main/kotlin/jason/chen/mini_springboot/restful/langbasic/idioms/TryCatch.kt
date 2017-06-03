package jason.chen.mini_springboot.restful.langbasic.idioms

/**
 * Created by mvasic on 6/5/16.
 */
fun tryCatchExample() {
    val carModels = listOf("BMW", "Fiat", "Mercedes", "Opel", "Renault")
    for (carModel in carModels) {
        try {
        } catch(e: Exception) {
            println(e.message)
        }
    }
}
