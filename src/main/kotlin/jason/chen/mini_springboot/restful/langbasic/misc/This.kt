package jason.chen.mini_springboot.restful.langbasic.misc

/**
 * Created by milosvasic on 8/8/16.
 */
class ThisExample {

    var a: String = "Default a"

    fun changeA(a: String){
        this@ThisExample.a = a
    }

}
