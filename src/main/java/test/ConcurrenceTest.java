package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

/**
 * Created by liangziyan on 2017/9/17.
 */
public class ConcurrenceTest {

//    FutureTask  f = new FutureTask();

    /**
     *  CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完成，这里就传入N。
     *  当我们调用CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await方法会阻塞当前线程，直到N变成零。
     */
    public static CountDownLatch countDownLatch= new CountDownLatch(2);

    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    public static void testCountDownLatch(){
       new TestThread("线程1",countDownLatch).start();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       new TestThread("线程2",countDownLatch).start();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行完");



       new TestThread("线程3",countDownLatch).start();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void testCyclicBarrier(){
        new TestThread("线程2",cyclicBarrier).start();
        new TestThread("线程1",cyclicBarrier).start();
        new TestThread("线程3",cyclicBarrier).start();
    }

    public static void main(String[] a) {
        //testCountDownLatch();
        testCyclicBarrier();
    }
}


class TestThread extends  Thread{
    private String name;
    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;

    public TestThread(String name,CountDownLatch countDownLatch){
        this.name = name;this.countDownLatch = countDownLatch;
    }

    public TestThread(String name,CyclicBarrier cyclicBarrier){
        this.name = name;this.cyclicBarrier = cyclicBarrier;
    }



    public void test1(){
        System.out.println(name+":执行当前线程数为："+countDownLatch.getCount());
        countDownLatch.countDown();

    }

    public void test2(){
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(name);

    }







    public void run(){
        test2();
    }
}



