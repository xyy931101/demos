package com.examplexyy.demo.concurrent.concurrent;

import java.util.concurrent.*;

/**
 * @Author: Xiongyy
 * @Date: 2021/2/20 20:50
 * .............................................
 * 佛祖保佑             永无BUG
 */
public class Renderer {

    private  final ExecutorService executorService;

    public Renderer(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Renderer renderer = new Renderer(Executors.newFixedThreadPool(10));
        renderer.rendererPage();
    }

    void rendererPage() throws InterruptedException, ExecutionException {
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        for (int i = 0; i < 10; i++) {
            completionService.submit(() -> {
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName());
                return 1;
            });
        }
        for (int j = 0; j < 10; j++) {
            Future<Integer> take = completionService.take();
            System.out.println(take.get() + Thread.currentThread().getName());
        }


        System.out.println("打印完成。。。。。。。。。。");
    }
}
