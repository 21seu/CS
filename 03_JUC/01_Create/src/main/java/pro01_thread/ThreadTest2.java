package pro01_thread;

/**
 * 创建两个分线程，一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 */
public class ThreadTest2 {

    public static void main(String[] args) {
        //创建Thread类匿名子类的方式
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) System.out.println(Thread.currentThread().getName() + "====>" + i);
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 != 0) System.out.println(Thread.currentThread().getName() + "====>" + i);
                }
            }
        }.start();
    }
}
