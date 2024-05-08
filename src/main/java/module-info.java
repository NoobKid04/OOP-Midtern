module com.example.oop_project {
  requires javafx.controls;
  requires javafx.fxml;
  requires jsapi;

  requires org.controlsfx.controls;
  requires com.google.gson;
  requires org.apache.httpcomponents.httpcore;
  requires org.apache.httpcomponents.httpclient;
  requires javafx.media;
  requires org.json;

  opens Dictionary.MainApp to javafx.fxml;
    exports Dictionary.MainApp;
  exports Game.Anagram;
  opens Game.Anagram to javafx.fxml;
  exports Dictionary.Utilities;
  opens Dictionary.Utilities to javafx.fxml;
}