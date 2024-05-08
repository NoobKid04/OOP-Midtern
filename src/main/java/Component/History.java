package Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class History {
  private static final int MAX_HISTORY_SIZE = 100; // Số lượng tối đa các mục trong lịch sử
  private static LinkedList<String> historyList = new LinkedList<>(); // Sử dụng LinkedList thay vì ArrayList

  public static void add(String word) {
    remove(word); // Loại bỏ từ khỏi lịch sử nếu đã tồn tại
    historyList.addFirst(word); // Thêm từ vào đầu danh sách lịch sử
    trimHistory(); // Cắt bớt lịch sử nếu vượt quá số lượng tối đa
  }

  public static void remove(String word) {
    historyList.remove(word); // Loại bỏ từ khỏi lịch sử
  }

  public static String[] getList() {
    return historyList.toArray(new String[0]); // Chuyển danh sách lịch sử sang mảng
  }

  private static void trimHistory() {
    while (historyList.size() > MAX_HISTORY_SIZE) {
      historyList.removeLast(); // Loại bỏ các mục cuối cùng nếu lịch sử vượt quá kích thước tối đa
    }
  }
}

