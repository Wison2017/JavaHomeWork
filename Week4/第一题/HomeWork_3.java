package HomeWork03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * 使用CompletableFuture
 */
public class HomeWork_3 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Integer result = CompletableFuture.supplyAsync(() -> {return sum();}).join();
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");    }

    private static int sum() {
        return 999;
    }
}
