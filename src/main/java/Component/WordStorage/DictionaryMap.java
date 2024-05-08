package Component.WordStorage;
import java.util.*;

public class DictionaryMap {
  private static final Map<String, String> map = new TreeMap<>();
  public static void add(String word, String meaning) {
    map.put(word, meaning);
  }

  public static void delete(String word) {
    map.remove(word);
  }
  public static String[] getKeys() {
    return map.keySet().toArray(new String[map.size()]);
  }

  public static Boolean exist(String word) {
    return map.containsKey(word);
  }

  public static String getRandom() {
    List<String> keyList = new ArrayList<>(map.keySet());
    Random random = new Random();
    int randomIndex = random.nextInt(keyList.size());
    return keyList.get(randomIndex).toLowerCase();
  }
  public static String getRandomWithLength(int minLength, int maxLength) {
    String randomWord = DictionaryMap.getRandom(); // Đây là một ví dụ. Bạn có thể thay thế bằng cách khác để chọn từ ngẫu nhiên.

    // Kiểm tra độ dài của từ và đảm bảo nằm trong phạm vi [minLength, maxLength]
    while (randomWord.length() < minLength || randomWord.length() > maxLength) {
      randomWord = DictionaryMap.getRandom(); // Chọn từ mới nếu độ dài không đáp ứng yêu cầu
    }
    return randomWord;
  }
}
