package pro01_sync;

/**
 * 使用同步方法解决线程安全问题
 * <p>
 * 总结：
 * 1、同步方法仍然涉及到同步监视器，只是不需要我们显示的声明
 * 2、非静态的同步方法，同步监视器是：this
 *   静态的同步方法，同步监视器是：当前类本身
 */
public class SyncMethodTest03 {

    private static int tickets = 100;
    private static Object obj = new Object();

    private synchronized void sync() { //这里的同步监视器就是this
        if (tickets > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + tickets);
            tickets--;
        }
    }

    public static void main(String[] args) {
        SyncMethodTest03 test = new SyncMethodTest03();
        new Thread(() -> {
            while (true) {
                test.sync();
            }
        }, "A").start();

        new Thread(() -> {
            while (true) {
                test.sync();
            }
        }, "B").start();
    }
}
