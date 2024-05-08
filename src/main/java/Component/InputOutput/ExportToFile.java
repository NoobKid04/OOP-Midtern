package Component.InputOutput;

import Component.WordStorage.DictionaryMap;
import Component.WordStorage.Trie;
import Component.WordStorage.TrieNode;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ExportToFile {
  public static void export(File file) {
    try {
      if (file == null) {
        String Path = "src/main/resources/dictionaries.txt";
        file = new File(Path);
        file.createNewFile();
      }
      FileWriter writer = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(writer);
      String[] word = DictionaryMap.getKeys();
      for (String tmp : word) {
        TrieNode node = Trie.find(tmp);
        bufferedWriter.write(node.getFullWord() + '\n');
        bufferedWriter.write(node.getSpelling() + '\n');
        bufferedWriter.write(node.getAudio() + '\n');
        bufferedWriter.write(node.getMeaning() + '\n');
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
