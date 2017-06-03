package jason.chen.mini_springboot.restful.langbasic.syntax

/**
 * Created by mvasic on 6/5/16.
 */
fun powIfDouble(x: Any): Double {
    if (x is Double) {
        return Math.pow(x, 2.0);
    }
    return 0.0;
}
