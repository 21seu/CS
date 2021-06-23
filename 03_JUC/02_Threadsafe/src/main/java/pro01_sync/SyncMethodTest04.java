package pro01_sync;

public class SyncMethodTest04 {

    public static void main(String[] args) {
        MyThread2 t1 = new MyThread2("A");
        MyThread2 t2 = new MyThread2("B");
        MyThread2 t3 = new MyThread2("C");
        t1.start();
        t2.start();
        t3.start();
    }
}

class MyThread2 extends Thread {

    private static int tickets = 100;

    public MyThread2(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    private static synchronized void show() { //同步监视器：当前类SyncMethodTest04.class
    //private synchronized void show() { //同步监视器：t1,t2,t3
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
}