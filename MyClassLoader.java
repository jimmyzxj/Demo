package rpc;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class MyClassLoader extends ClassLoader {
    private String classpath;//要加载class文件的路径
    private Set<String> specialClass;//指定加载特定的class

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        //如果类没有加载并且包含指定的类名，用自定义类加载
        if (c == null && specialClass.contains(name)) {
            c = findClass(name);
            if (c == null) throw new ClassNotFoundException("找不到类");
            return c;
        }
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) {
        //获取加载class文件的bytes
        byte[] bytes = getClassBytes(name);
        //用用byte数据转换为Class对象
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getClassBytes(String name) {
        FileInputStream input = null;
        ByteArrayOutputStream out = null;
        byte[] bytes = null;
        try {
            input = new FileInputStream(name);
            out = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = input.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            bytes = out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) input.close();
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
