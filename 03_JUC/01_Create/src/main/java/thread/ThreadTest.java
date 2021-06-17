package thread;

/**
 * 多线程的创建，方式1：集成Thread类
 * 1、创建一个继承于Thread类的子类
 * 2、重写Thread类的run()
 * 3、创建Thread类的子类的对象
 * 4、通过此对象调用start()
 */
public class ThreadTest {
    public static void main(String[] args) {
        //3、创建Thread类的子类的对象
        MyThread thread = new MyThread();
        //4、通过此对象调用start() ①启动当前线程 ②调用当前线程的run方法
        //一个线程的start只能调用一次，不然会抛出异常，底层维护了一个threadStatus；需要再new一个线程类
        thread.start();
        //不能直接调用run方法启动线程，因为他根本不会启动
        //thread.run();
        System.err.println(Thread.currentThread().getName() + "====>" + "main0");
    }
}

//1、创建一个继承于Thread类的子类
class MyThread extends Thread {

    @Override
    public void run() {
        //2、重写Thread类的run()
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) System.out.println(Thread.currentThread().getName() + "====>" + i);
        }
    }
}
