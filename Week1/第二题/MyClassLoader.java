import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 功能描述：自定义一个ClassLoader，用于加载Hello.xlass，该字节码文件的每个字节(x = 255 - x)处理；
 * 255二进制表示为: 1111 1111, 255 - x 相当于将字节 x 的每个位取反, 所以在读取Hello.xlass的
 * 每个字节之后，需要取反还原，可通过每个字节与 0B11111111 异或实现还原；
 *
 * @Author: Wilson
 * @date: 2021/06/27
 */
public class MyClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        String path = "/Users/wison/Desktop/Java训练营/HomeWork/Week1/第二题/Hello.xlass";
        Class clazz = new MyClassLoader().findClass(path);
        // 通过反射调用Hello.class的hello方法
        Method method = clazz.getMethod("hello");
        method.invoke(clazz.newInstance());
    }

    @Override
    protected Class<?> findClass(String path) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(path);
            byte b1 = (byte) 0B11111111;
            byte input[] = new byte[1024];
            int length = fis.read(input);
            for (int i = 0; i < length; i++) {
                input[i] = (byte) (input[i] ^ b1);
            }
            return defineClass("Hello", input, 0, length);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}