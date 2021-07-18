package HomeWork03;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * 使用CountDownLatch
 */
public class HomeWork_2 {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        FutureTask<Integer> futureTask = new FutureTask<>(new TaskWithCountDownLatch(countDownLatch));
        Thread thread1 = new Thread(futureTask);
        thread1.start();
        countDownLatch.await();
        System.out.println("异步计算结果为：" + futureTask.get());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}

class TaskWithCountDownLatch implements Callable<Integer> {
    private CountDownLatch countDownLatch;

    public TaskWithCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Integer call() throws Exception {
        countDownLatch.countDown();
        return 888;
    }
}