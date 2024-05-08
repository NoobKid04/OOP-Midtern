package Component.WordStorage;

import java.util.TreeMap;

public class TrieNode {
   protected TreeMap<Character, TrieNode> child = new TreeMap<>();
  protected Boolean isEndOfWord, bookmarked;
  protected String fullWord, meaning, spelling, audio;
  protected int numPrefix;
  TrieNode() {
    isEndOfWord = bookmarked = false;
    fullWord = meaning = spelling = audio = "";
    numPrefix = 0;
  }

  public Boolean getBookmarked() {
    return bookmarked;
  }

  public void setBookmarked(Boolean bookmarked) {
    this.bookmarked = bookmarked;
  }

  public String getFullWord() {
    return fullWord;
  }

  public String getMeaning() {
    return meaning;
  }

  public String getSpelling() {
    return spelling;
  }

  public String getAudio() {
    return audio;
  }
}