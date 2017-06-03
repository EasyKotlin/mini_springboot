package jason.chen.mini_springboot.restful.langbasic.idioms


/**
 * Created by mvasic on 6/5/16.
 */
class MyLazy {
    val lazyVar: String by lazy() {
        println("We called lazy initialization.")
        "Lazy value"
    }
}


