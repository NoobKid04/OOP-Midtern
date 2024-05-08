package Component;

public class Standalization {
  public static String formatString(String s) {
    if (s == null || s.isBlank()) {
      return s;
    }

    if (s.charAt(0) == '@') {
      s=s.substring(1);
    }
    // Loại bỏ khoảng trắng ở đầu và cuối chuỗi
    s = s.trim();

    // Chuyển ký tự đầu tiên thành chữ hoa
    char firstChar = Character.toUpperCase(s.charAt(0));

    // Tạo một StringBuilder để xây dựng chuỗi kết quả
    StringBuilder res = new StringBuilder(s.length());
    res.append(firstChar); // Thêm ký tự đầu tiên đã được chuyển thành chữ hoa

    // Chuyển các ký tự còn lại thành chữ thường
    for (int i = 1; i < s.length(); i++) {
      char c = s.charAt(i);
      res.append(Character.toLowerCase(c));
    }
    return res.toString();
  }

}
