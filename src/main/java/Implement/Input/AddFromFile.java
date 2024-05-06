package Implement.Input;

import Implement.WordFormatter;
import Implement.WordStorage.DictionaryMap;
import Implement.WordStorage.Trie.Trie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AddFromFile {
  public static void add(File file) {
    try {
      if (file == null) {
        String Path = "src/main/resources/dictionaries.txt";
        file = new File(Path);
      }
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String word, spelling, audio;
      StringBuilder meaning;
      while ((word = bufferedReader.readLine()) != null) {

        String[] s = word.split("\\s+");
        word = s[0].substring(1);
        word = WordFormatter.normalize(word);

        String a = "";
        for (int i = 1; i<s.length; i++) {
          a += s[i] + " ";
        }
        spelling = a;

        audio = "https://api.dictionaryapi.dev/media/pronunciations/en/" + word + "-us.mp3";

        meaning = new StringBuilder();
        String tmp;
        while ((tmp = bufferedReader.readLine()) != null) {
          if (tmp.isBlank()) {
            break;
          }
          meaning.append(tmp).append("\n");
        }
        Trie.add(word, spelling, meaning.toString(), audio);
        DictionaryMap.add(word, meaning.toString()); // Thêm vào Map
      }
      bufferedReader.close();
    } catch (Exception e)
    {
      System.out.println("Unknown Error");
    }
  }
}