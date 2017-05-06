package com.sevenconc.threads.day1;

import java.util.Random;

public class Philosopher extends Thread {
  private Chopstick first, second;
  private Random random;

  public Philosopher(Chopstick left, Chopstick right) {
    if(left.getId() < right.getId()) {
      first = left;
      second = right;
    } else {
      first = right;
      second = left;
    }

    random = new Random();
  }

  public void run() {
    try {
      while(true) {
        Thread.sleep(random.nextInt(1000)); // think for a while
        synchronized (first) { // grab left chopstick
          synchronized (second) { // grab right chopstick
            Thread.sleep(random.nextInt(1000)); // eat for a while
          }
        }
      }
    } catch(InterruptedException e) {}
  }

  class Chopstick {
    int id;

    int getId() {
      return id;
    }
  }
}
