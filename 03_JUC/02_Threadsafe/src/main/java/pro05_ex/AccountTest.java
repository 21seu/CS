package pro05_ex;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 银行有一个账户
 * 有两个储户分别向同一个账户存3000元，每次存1000，存3次，每次存完打印账户余额
 * <p>
 * 分析：
 * 1、多线程问题 2、有共享数据 3、所以有线程安全问题  4、需要考虑线程安全问题怎么解决：三种方式
 *
 * @author ftj
 */
public class AccountTest {
    private static double account = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {

            try {
                lock.lock();
                for (int i = 0; i < 3; i++) {
                    account += 1000;
                    Thread.sleep(1000);
                    System.out.println("目前账户余额：" + account + "元，由账户1存进去的");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "储户1").start();
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 3; i++) {
                    account += 1000;
                    Thread.sleep(1000);
                    System.out.println("目前账户余额：" + account + "元，由账户2存进去的");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "储户2").start();
    }
}
