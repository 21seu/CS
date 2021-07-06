package pro04_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的第三种方式：实现Callable接口
 * <p>
 * Callable接口方式和Runnable接口方式对比：
 * ①call()方法可以有返回值②call()方法可以抛出异常，被外面的操作捕获，获取异常信息 ③、支持泛型
 * Created by fengtj on 2021/7/6 21:23
 */
//1、创建一个Callable的实现类
public class CallbaleTest01 implements Callable<Integer> {

    //2、实现call()方法，将此线程需要执行的操作声明再call()中
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}

class MainThread {
    public static void main(String[] args) {
        //3、创建Callable实现类的对象
        CallbaleTest01 c1 = new CallbaleTest01();
        //4、将此Callable实现类的对象作为参数传递到FutureTask构造器中，创建FutureTask的对象
        FutureTask<Integer> task = new FutureTask<Integer>(c1);
        //5、将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并start()调用
        new Thread(task).start();
        try {
            //6、获取Callable中call方法的返回值
            //get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值
            Integer sum = task.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}