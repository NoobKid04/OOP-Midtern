package Implement;

public class Standardization {
    public static String normalize(String str) {
        if (str.isBlank()) return str;
        str = str.trim();
        StringBuilder res = new StringBuilder(str);
        for (int i = 0; i < res.length(); i++) {
            char c = res.charAt(i);
            res.setCharAt(i, Character.toLowerCase(c));
        }
        char first = res.charAt(0);
        res.setCharAt(0, Character.toUpperCase(first));
        return res.toString();
    }
}
