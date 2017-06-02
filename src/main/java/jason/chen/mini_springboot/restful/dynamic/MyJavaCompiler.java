package jason.chen.mini_springboot.restful.dynamic;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.stereotype.Service;

/**
 * Created by jack on 2017/6/2.
 */
@Service
public class MyJavaCompiler extends ClassLoader {
    private final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    private final FileManager manager = new FileManager(this.compiler);  //自定义FileManager

    public Class compileJavaSource(String name, String code) {
        List<Source> list = new ArrayList<Source>();
        list.add(new Source(name, JavaFileObject.Kind.SOURCE, code)); //输入
        StringWriter stringWriter = new StringWriter();
        this.compiler.getTask(stringWriter, this.manager, null, null, null, list).call();
        Output mc = this.manager.getOutput(name);    //输出
        System.out.println(mc);
        if (mc != null) {
            byte[] array = mc.toByteArray();  //转换成byte[]
            return defineClass(name, array, 0, array.length);  //需要继承ClassLoader
        }
        return null;
    }

    public Class compileClass(String name, String code) {
        List<Source> list = new ArrayList<Source>();
        list.add(new Source(name, Kind.CLASS, code)); //输入
        StringWriter stringWriter = new StringWriter();
        this.compiler.getTask(stringWriter, this.manager, null, null, null, list).call();
        Output mc = this.manager.getOutput(name);    //输出
        if (mc != null) {
            byte[] array = mc.toByteArray();  //转换成byte[]
            return defineClass(name, array, 0, array.length);  //需要继承ClassLoader
        }
        return null;
    }

    public class FileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
        protected final Map<String, Output> map = new HashMap<String, Output>();

        public FileManager(JavaCompiler compiler) {
            super(compiler.getStandardFileManager(null, null, null));
        }  //取得文件管理器

        @Override
        public Output getJavaFileForOutput
            (Location location, String name, JavaFileObject.Kind kind, FileObject source) {
            Output mc = new Output(name, kind);   //与文件连接
            this.map.put(name, mc);
            return mc;
        }

        public Output getOutput(String name) {
            return map.get(name);
        }
    }

    public class Output extends SimpleJavaFileObject {
        private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        public Output(String name, Kind kind) {
            super(URI.create("dynamic:///" + name.replace('.', '/') + kind.extension), kind);
        } //URI

        byte[] toByteArray() {  //外部调用，生成Class
            return this.baos.toByteArray();
        }

        @Override
        public ByteArrayOutputStream openOutputStream() {
            return this.baos;
        }
    }

    public class Source extends SimpleJavaFileObject {
        private final String content;

        public Source(String name, Kind kind, String content) {
            super(URI.create("dynamic:///" + name.replace('.', '/') + kind.extension), kind);
            this.content = content;
        }

        @Override
        public CharSequence getCharContent(boolean ignore) {
            return this.content;
        }
    }
}
