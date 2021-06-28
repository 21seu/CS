package pro05_ex;

/**
 * 银行有一个账户
 * 有两个储户分别向同一个账户存3000元，每次存1000，存3次，每次存完打印账户余额
 */
class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    //存钱的方法
    public synchronized void deposit(double amt) {
        if (amt > 0) {
            balance += amt;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "：存钱成功，余额为：" + balance);
        }
    }
}

class Customer extends Thread {

    private Account account;

    public Customer(Account account, String name) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            account.deposit(100);
        }
    }
}

public class AccountTest02 {
    public static void main(String[] args) {
        Account account = new Account(0);
        Customer c1 = new Customer(account,"甲");
        Customer c2 = new Customer(account,"乙");
        c1.start();
        c2.start();
    }
}
