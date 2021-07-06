package pro02_consumer_productor;

/**
 * Created by fengtj on 2021/7/5 23:02
 */
class Clerk2 {
    private int product = 0;

    public synchronized void products() {
        if (product < 20) {
            product++;
            System.out.println(Thread.currentThread().getName() + "：开始生产第" + product + "个产品");
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumers() {
        if (product > 0) {
            System.out.println(Thread.currentThread().getName() + "：开始消费第" + product + "个产品");
            product--;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Produce implements Runnable {

    private Clerk2 clerk2;

    public Produce(Clerk2 clerk2) {
        this.clerk2 = clerk2;
    }

    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //生产产品
            clerk2.products();
        }
    }
}

class Consumer2 implements Runnable {

    private Clerk2 clerk2;

    public Consumer2(Clerk2 clerk2) {
        this.clerk2 = clerk2;
    }

    public void run() {
        for (;;){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //消费产品
            clerk2.consumers();
        }
    }
}

public class ProductTest02 {

    public static void main(String[] args) {
        Clerk2 clerk2 = new Clerk2();
        Produce p1 = new Produce(clerk2);
        new Thread(p1, "生产者1").start();
        new Thread(new Consumer2(clerk2), "消费者1").start();

    }
}
