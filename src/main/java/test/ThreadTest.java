package test;

import java.util.concurrent.Semaphore;

/**
 * Created by liangziyan on 2017/9/6.
 */
public class ThreadTest {

    public static void main(String[] args){

        Semaphore semaphore = new Semaphore(2);
        for(int i=0;i<10;i++){
            new MyThread(semaphore).start();
        }
    }

}

class MyThread extends Thread{

    private Semaphore semaphore = null;

    public MyThread(Semaphore semaphore){
       this.semaphore = semaphore;
    }

    public void run(){
        try{
            semaphore.acquire();
            System.out.println(getName());
            sleep(2000);

        }catch (Exception w){

        }
       semaphore.release();

    }
}

