package pro05_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建线程的方法四：线程池
 * <p>
 * 好处：
 * 1、提高响应速度（减少了新线程的创建时间）
 * 2、降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3、便于线程管理：ExecutorService的实现类们可以设置属性 如ThreadPoolExecutor
 * Created by fengtj on 2021/7/6 22:00
 */
class NumberThread implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}

public class ThreadPool {

    public static void main(String[] args) {
        //1、提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        System.out.println(service.getClass());//class java.util.concurrent.ThreadPoolExecutor

        ThreadPoolExecutor pool = (ThreadPoolExecutor) service;
        //pool.setCorePoolSize(15);
        //pool.setKeepAliveTime();
        //2、执行指定的线程的操作，需要提供实现Runnable接口或者Callable接口实现类的对象
        //service.submit();//适合使用Callable
        service.execute(new NumberThread());//适合使用Runnable
        service.execute(() -> {
            for (int i = 0; i < 100; i++) {
                if (i % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + "---->" + i);
                }
            }
        });
        //关闭连接池
        service.shutdown();
    }
}
