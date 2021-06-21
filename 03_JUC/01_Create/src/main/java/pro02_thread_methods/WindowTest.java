package pro02_thread_methods;

/**
 * 创建三个窗口买票，总票数为100张
 */
public class WindowTest {

    public static void main(String[] args) {
        Window w1 = new Window("窗口1");
        Window w2 = new Window("窗口2");
        Window w3 = new Window("窗口3");

        w1.start();
        w2.start();
        w3.start();

    }
}

class Window extends Thread {
    private static int tickets = 100;

    public Window(String name){
        super(name);
    }
    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                System.out.println(getName() + "：买票，票号为：" + tickets);
                tickets--;
            } else {
                break;
            }
        }
    }
}