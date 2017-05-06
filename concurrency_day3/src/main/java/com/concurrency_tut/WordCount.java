package main.java.com.concurrency_tut;

import java.util.HashMap;
import java.util.Map;

public class WordCount {
  private static final Map<String, Integer> counts = new HashMap<>();

  public static void main(String... args) {
    Iterable<Page> pages = new Pages(100000, "Users/ryanthames/enwiki.xml");

    for(Page page : pages) {
      Iterable<String> words = new Words(page.getText());

      for(String word : words) {
        countWord(word);
      }
    }
  }

  private static void countWord(String word) {
    Integer currentCount = counts.get(word);

    if(currentCount == null) {
      counts.put(word, 1);
    } else {
      counts.put(word, currentCount + 1);
    }
  }
}
