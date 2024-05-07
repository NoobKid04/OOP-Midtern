import java.util.ArrayList;
import java.util.Collections;

public final class Bookmark {
  private static ArrayList<String> savedList = new ArrayList<>();

  public static void marked(String markedword) {
    savedList.add(markedword);
  }
  public static void unmark(String unmarkword) {
    savedList.remove(unmarkword);
  }
  public static boolean find(String word) {
    return savedList.contains(word);
  }

  public static String[] list() {
      Collections.sort(savedList);
      return savedList.toArray(new String[savedList.size()]);
  }
}
