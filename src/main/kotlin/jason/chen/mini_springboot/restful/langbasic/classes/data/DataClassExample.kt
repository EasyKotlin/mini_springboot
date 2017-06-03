package jason.chen.mini_springboot.restful.langbasic.classes.data

/**
 * Created by milosvasic on 6/9/16.
 */
data class Employee(val name: String, val age: Int) {}

data class Worker(val name: String = "Unknown", val age: Int = 1970) {}
