package pro02_singleton;

/**
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 */
public class BankTest {
}

class Bank {
    private Bank() {

    }

    private static Bank instance = null;

    public static Bank getInstance() {
        //方式1：效率稍差
        /*synchronized (Bank.class) {
            if (instance == null){
                instance = new Bank();
            }
            return instance;
        }*/
        //方式2：效率稍高
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}