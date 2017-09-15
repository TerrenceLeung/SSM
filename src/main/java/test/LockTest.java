package test;

import java.util.concurrent.locks.*;

/**
 * Created by liangziyan on 2017/9/8.
 */
public class LockTest {

    Lock lock = new ReentrantLock();

    public static void main(String[] args){
        Thread thread = new Thread("A"){
            public void run(){
                LockSupport.parkNanos(100000000);
                LockSupport.parkUntil(null,100000000);
                System.out.println(this.getName());
            }
        };

        Thread thread1 = new Thread("V"){
            public void run(){
                System.out.println(this.getName());
            }
        };
        thread.start();
        thread1.start();






    }
}



