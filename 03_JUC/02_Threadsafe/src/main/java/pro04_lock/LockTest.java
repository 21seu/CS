package pro04_lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式3：Lock锁 ---JDK5.0新增
 * 1、面试题：synchronized与ReentrantLock的异同
 *  相同：都可以解决线程安全问题
 *  不同：synchronized在执行完相应的代码逻辑之后自动释放同步监视器
 *       ReentrantLock需要手动启动同步和手动结束同步
 */
public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t1.start();
        t2.start();
        t3.start();
    }
}

class Window implements Runnable {
    private int tickets = 100;
    //1、实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                //2、调用锁定的方法lock()方法
                lock.lock();
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + tickets);
                    tickets--;
                } else {
                    break;
                }
            } finally {
                //3、调用解锁的方法
                lock.unlock();
            }
        }
    }
}
