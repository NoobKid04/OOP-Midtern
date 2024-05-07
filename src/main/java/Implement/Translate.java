package Implement;

import javafx.application.Platform;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.net.URLEncoder;

public class Translate {
    public void translate(String text, String start, String end, ComeBack Come) {

        /**
         *  tạo luồng;
         */
        Thread l = new Thread(() -> {
            HttpClient create_http = HttpClients.createDefault();
            try {
                String code_End = URLEncoder.encode(text, "UTF-8");
                String str_Ulr = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" +
                        start + "&tl=" + end + "&dt=t&q=" + code_End;

                HttpGet httpGet = new HttpGet(str_Ulr);
                HttpResponse execute = create_http.execute(httpGet);
                HttpEntity entity = execute.getEntity();

                String s = EntityUtils.toString(entity);
                JSONArray jsonArray = new JSONArray(s);
                if (jsonArray != null && jsonArray.length() > 0) {
                    StringBuilder translatedTextBuilder = new StringBuilder();
                    JSONArray translations = jsonArray.getJSONArray(0);
                    for (int i = 0; i < translations.length(); i++) {
                        JSONArray translation = translations.getJSONArray(i);
                        String translatedSegment = translation.getString(0);
                        translatedTextBuilder.append(translatedSegment).append(" ");
                    }
                    Platform.runLater(() -> Come.onBack(translatedTextBuilder.toString().trim()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        l.start();
    }
}
