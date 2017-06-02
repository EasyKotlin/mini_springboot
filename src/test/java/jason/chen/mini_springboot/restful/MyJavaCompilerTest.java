package jason.chen.mini_springboot.restful;

import jason.chen.mini_springboot.restful.dynamic.MyJavaCompiler;

/**
 * Created by jack on 2017/6/2.
 */
public class MyJavaCompilerTest {
    public static void main(String[]args){
        MyJavaCompiler MyJavaCompiler =new MyJavaCompiler();
        Class clz = MyJavaCompiler.compileClass("ktfiles/HelloKotlinKt" ,null);
    }
}
