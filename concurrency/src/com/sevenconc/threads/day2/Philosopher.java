package com.sevenconc.threads.day2;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
  private ReentrantLock leftChopstick, rightChopstick;
  private Random random;

  public Philosopher(ReentrantLock left, ReentrantLock right) {
    this.leftChopstick = left;
    this.rightChopstick = right;

    random = new Random();
  }

  public void run() {
    try {
      while(true) {
        Thread.sleep(random.nextInt(1000)); // think for a while
        leftChopstick.lock();

        try {
          if(rightChopstick.tryLock(1000, TimeUnit.MILLISECONDS)) {
            try {
              Thread.sleep(random.nextInt(1000));
            } finally {
              rightChopstick.unlock();
            }
          } else {
            // didn't ge tthe right chopstick, go back to thinking
          }
        } finally {
          leftChopstick.unlock();
        }
      }
    } catch(InterruptedException e) {}
  }
}
