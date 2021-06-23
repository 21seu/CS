package pro01_sync;

/**
 * 例子：创建三个窗口卖票，总票数为100张，使用实现Runnable接口的方式
 * <p>
 * 1、问题：卖票过程中，出现了重票、错票---->出现了线程的安全问题
 * 2、问题出现的原因：当某个线程操作车票的过程中，尚未操作完成时，其他线程也参与进来了，也操作车票
 * 3、如何解决：当一个线程a在操作tickets时，其他线程不能参与进来；直到线程a操作完tickets，其他线程
 * 才可以操作tickets。这种情况即使线程a出现了阻塞，也不能被改变
 * <p>
 * 4、在Java中，我们通过同步机制，在解决线程的安全问题
 * 方式1：同步代码块
 * synchronized(同步监视器){  //同步监视器：锁（任何一个类的对象都可以充当一个锁）
 * //需要被同步的代码（操作共享数据的代码）
 * }
 * 方式2：同步方法
 */
public class SyncTest01 {
    private static int tickets = 100;
    private static Object obj = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                synchronized (obj) {
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
                }
            }
        }, "A").start();

        new Thread(() -> {
            while (true) {
                synchronized (obj) {
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
                }
            }
        }, "B").start();
    }
}
