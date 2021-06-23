package pro01_sync;

public class SyncTest02 {

    public static void main(String[] args) {
        MyThread t1 = new MyThread("A");
        MyThread t2 = new MyThread("B");
        MyThread t3 = new MyThread("C");
        t1.start();
        t2.start();
        t3.start();
    }
}

class MyThread extends Thread {

    private static int tickets = 100;

    private static Object obj = new Object();

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
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
    }
}