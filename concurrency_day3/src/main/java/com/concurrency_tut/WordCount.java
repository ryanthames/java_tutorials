package main.java.com.concurrency_tut;

import java.util.concurrent.*;

public class WordCount {
  private static final int NUM_COUNTERS = 2;

  public static void main(String... args) throws InterruptedException {
    BlockingQueue<Page> queue = new ArrayBlockingQueue<>(100);
    ConcurrentMap<String, Integer> counts = new ConcurrentHashMap<>();

    ExecutorService executor = Executors.newCachedThreadPool();

    for(int i = 0; i < NUM_COUNTERS; ++i) {
      executor.execute(new Counter(queue, counts));
    }

    Thread parser = new Thread(new Parser(queue));

    parser.start();
    parser.join();

    for(int i = 0; i < NUM_COUNTERS; ++i) {
      queue.put(new PoisonPill());
    }

    executor.shutdown();
    executor.awaitTermination(10L, TimeUnit.MINUTES);
  }
}
