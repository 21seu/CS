package pro01;

/**
 * Created by fengtj on 2021/6/29 23:02
 * 线程通信的例子：使用两个线程打印1-100。交替打印
 * wait() 一旦进入此方法，当前线程就进入阻塞状态，并释放同步监视器。
 * notify() 一旦执行此方法，就会唤醒被wait的一个线程，如果有多个线程被wait则唤醒优先级高的
 * notifyAll() 一旦执行此方法，就会唤醒所有被wait的线程
 *
 * 1、这三个方法必须使用在同步代码块或同步方法中。
 * 2、这三个方法的调用者必须是同步代码块或者同步方法中的同步监视器，否则会出现非法异常。
 *
 * 面试题：sleep()和wait()的异同
 *  1、相同点：一旦执行方法，都可以让当前线程进入阻塞状态
 *  2、不同点：1）声明的位置不同：sleep()在Thread类中；wait()在Object()类中。
 *           2）调用的范围不同：sleep()可以在任何需要的场景下调用；wait()必须在同步代码块或同步方法中调用
 *           3）关于是否释放同步监视器：如果两个方法都使用在同步代码块或者同步方法中，sleep()不会释放同步监视器；而wait()会释放同步监视器
 */
public class OneForOh {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);
        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t2.start();
    }
}

class Number implements Runnable {
    private int number = 1;

    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + "：" + number);
                    number++;
                    try {
                        //使得调用如下wait方法的线程进入阻塞状态
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}