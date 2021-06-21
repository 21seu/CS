package pro03_runnable;

/**
 * 创建三个窗口买票，总票数为100张，使用Runnable接口方式
 * 存在线程安全问题，待解决
 */
public class WindowTest1 {

    public static void main(String[] args) {
        Windows w = new Windows();
        Thread t1 = new Thread(w, "窗口1");
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class Windows implements Runnable {

    private int tickets = 100;

    public void run() {
        while (true) {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "：买票，票号为：" + tickets);
                tickets--;
            } else {
                break;
            }
        }
    }
}