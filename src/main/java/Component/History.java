package Component;

import java.util.ArrayList;

public class History {
  private static final ArrayList<String> historyList = new ArrayList<>();
  public static void add(String word) {
    remove(word);
    historyList.add(0, word);
  }

  public static void remove(String exsistedword) {
    historyList.remove(exsistedword);
  }

  public static String[] getList() {
    return historyList.toArray(new String[historyList.size()]);
  }
}
