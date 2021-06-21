package pro03_runnable;

/**
 * 创建多线程的方法2：实现Runnable接口
 *  1、创建一个实现了Runnable接口的类
 *  2、实现类去实现Runnable接口中的抽象方法：run()
 *  3、创建实现类对象
 *  4、将此对象作为参数传递给Thread类的构造器中，创建Thread类的对象
 *  5、通过Thread类的对象去调用start()
 */
public class RunnableTest01 {
    public static void main(String[] args) {
        //3、创建实现类对象
        //4、将此对象作为参数传递给Thread类的构造器中，创建Thread类的对象
        //5、通过Thread类的对象去调用start()
        new Thread(new MyThread()).start();
    }
}

//1、创建一个实现了Runnable接口的类
class MyThread implements Runnable{

    //2、实现类去实现Runnable接口中的抽象方法：run()
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i%2==0){
                System.out.println(i);
            }
        }
    }
}
