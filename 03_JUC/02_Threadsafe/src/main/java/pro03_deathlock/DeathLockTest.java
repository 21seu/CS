package pro03_deathlock;

/**
 * 演示线程死锁的问题：
 * 1、死锁的理解：不同的线程分别占用对方需要的同步资源不放，都在等待对方放弃自己需要的同步资源，就形成了死锁
 *
 * 2、说明：出现死锁后，不会出现异常和提示，只是所有的线程都处于阻塞状态，无法继续
 *        我们使用同步时，要避免出现死锁
 */
public class DeathLockTest {

    public static void main(String[] args) {

        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();
        new Thread(() -> {
            synchronized (s1) {
                s1.append("a");
                s2.append("1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (s2) {
                    s1.append("b");
                    s2.append("2");
                    System.out.println(s1);
                    System.out.println(s2);
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (s2) {
                s1.append("c");
                s2.append("3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (s1) {
                    s1.append("d");
                    s2.append("4");
                    System.out.println(s1);
                    System.out.println(s2);
                }
            }
        }).start();
    }
}
