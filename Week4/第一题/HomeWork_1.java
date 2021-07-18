package HomeWork03;
import java.util.concurrent.*;

/**
 * 使用join
 */
public class HomeWork_1 {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        Thread thread1 = new Thread(futureTask);
        thread1.start();
        thread1.join();
        System.out.println("直接new一个线程");
        System.out.println("异步计算结果为：" + futureTask.get());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("==================================");
        System.out.println();
    }
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 888;
    }
}



