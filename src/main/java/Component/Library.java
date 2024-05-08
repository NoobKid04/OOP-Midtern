package Component;

import java.util.ArrayList;
import java.util.Collections;

public class Library {
  private static final ArrayList<String> libList = new ArrayList<>();
  public static void add(String word) {
    libList.add(word);
  }

  public static void remove(String word) {
    libList.remove(word);
  }
  public static boolean find(String word) {
    return libList.contains(word);
  }

  public static String[] getList() {
    Collections.sort(libList);
    return libList.toArray(new String[(int) libList.size()]);
  }
}
