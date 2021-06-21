package pro02_thread_methods;

/**
 * Thread的常用方法：
 * 1、start()：启动当前线程；调用当前线程的run()方法
 * 2、run()：通常需要重写Thread类中的此方法，将创建的线程执行的操作声明再此方法中
 * 3、currentThread()：静态方法，返回当前代码执行的线程
 * 4、getName()：获取当前线程的名字
 * 5、setName()：设置当前线程的名字
 * <p>
 * 6、yield()：释放当前cpu的执行权
 * 7、join()：在线程a中调用线程b的join方法，此时线程a就进入阻塞状态，直到线程b完全执行完之后，线程a才结束阻塞状态
 * 8、stop：已过时，当执行此方法时，强制结束当前线程
 * 9、sleep(long millitime)：让当前线程”睡眠“指定的milltime毫秒。在指定的时间内，当前线程阻塞状态
 * 10、isAlive()：判断当前线程是否存活
 *
 * 线程的优先级：
 *  1、
 *  MAX_PRIORITY 10
 *  MIN_PRIORITY 1
 *  NORM_PRIORITY 5
 *
 *  2、如何获取和设置当前线程的优先级：
 *      getPriority() 获取线程的优先级
 *      setPriority(int p) 获取线程的优先级
 *
 *  说明：高优先级的线程要抢占低优先级线程的执行权，但是只是从概率上来讲，高优先级的线程高概率的情况下被执行，并不意味着只有当高优先级的线程执行完后低优先级的才执行
 */
public class ThreadMethodTest {
    public static void main(String[] args) {
        MyThread thread = new MyThread("ftj----Thread");
        //设置分线程的优先级
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        //设置主线程的优先级
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "====>" + i);
            }
            if (i == 20) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("是否存活：" + thread.isAlive());
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                try {
                    //sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "====>" + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (i % 2 == 0) {
                //yield();
            }
        }
    }

    public MyThread(String name) {
        super(name);
    }
}
