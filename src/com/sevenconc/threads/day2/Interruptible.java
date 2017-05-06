package com.sevenconc.threads.day2;

import java.util.concurrent.locks.ReentrantLock;

public class Interruptible {
  final ReentrantLock l1 = new ReentrantLock();
  final ReentrantLock l2 = new ReentrantLock();

  Thread t1 = new Thread() {
    public void run() {
      try {
        l1.lockInterruptibly();
        Thread.sleep(1000);
        l2.lockInterruptibly();
      } catch(InterruptedException e) {
        System.out.println("t1 interrupted");
      }
    }
  };
}
