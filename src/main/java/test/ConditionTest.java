package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();
        test.new Consumer("消费者A").start();
        test.new Consumer("消费者B").start();
        test.new Consumer("消费者C").start();
        test.new Producer("生产者A").start() ;

    }

    class Consumer extends Thread{

        Consumer(String name){
            this.setName(name);
        }
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println(this.currentThread().getName()+":拿到锁");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.currentThread().getName()+":我在等一个新信号，释放锁");
                condition.await();
            } catch (InterruptedException e) {
            } finally{
                System.out.println(this.currentThread().getName()+":拿到一个信号");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.currentThread().getName()+":释放锁");
                lock.unlock();
            }
        }
    }

    class Producer extends Thread{
        Producer(String name){
            this.setName(name);
        }
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println(this.currentThread().getName()+":我拿到锁，开始生产");
                sleep(2000L);
//                condition.signalAll();
                condition.signalAll();
                System.out.println(this.currentThread().getName()+":生产完毕，我发出了一个信号");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally{
                lock.unlock();
            }
        }
    }
}