package Implement;

import java.util.ArrayList;

public class History {
  private static final ArrayList<String> historyList = new ArrayList<>();
  public static void add(String word) {
    delete(word);
    historyList.add(0, word);
  }

  public static void delete(String exsistedword) {
      historyList.remove(exsistedword);
  }

  public static String[] list() {
    return historyList.toArray(new String[historyList.size()]);
  }
}
