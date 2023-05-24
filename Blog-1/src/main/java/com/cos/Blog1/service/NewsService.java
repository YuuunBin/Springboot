package com.cos.Blog1.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cos.Blog1.model.NewsArticle;
import com.cos.Blog1.repository.NewsRepository;

@Service
public class NewsService {

   @Autowired
   private NewsRepository newsRepository;
   
   
   @Scheduled(cron = "* * 2 * * *")
   public void newsApi() throws ParseException {
      String clientId = "KHO56Io3Uy5iodrbzU8i"; 
      String clientSecret = "mGeduWKDNe"; 

      String text = null;
      try {
         text = URLEncoder.encode("원숭이두창", "UTF-8");
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException("검색어 인코딩 실패", e);
      }

      String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text 
            + "&display=10&start=1&sort=date";


      Map<String, String> requestHeaders = new HashMap<>();
      requestHeaders.put("X-Naver-Client-Id", clientId);
      requestHeaders.put("X-Naver-Client-Secret", clientSecret);
      String responseBody = get(apiURL, requestHeaders);

      JSONParser parser = new JSONParser();
      JSONObject jsonObject = (JSONObject) parser.parse(responseBody);
      JSONArray itemsArray = (JSONArray) jsonObject.get("items");
      for (int i = 0; i < itemsArray.size(); i++) {
         JSONObject line = (JSONObject) itemsArray.get(i);
         String originallink = (String) line.get("originallink");
         String desc = (String) line.get("description");
         String rexdesc = desc.replaceAll("<[/]?b>", "");
         String rexdesc1 = rexdesc.replaceAll("&apos;", "");

         String title = (String) line.get("title");
         String titlerex = title.replaceAll("<[/]?b>", "");
         System.out.println("타이틀 : " + titlerex);
         System.out.println("링크 : " + originallink);
         System.out.println("기사 내용 : " + rexdesc1 + "\n");
         create(titlerex, originallink, rexdesc1);
      }
   }

   public void create(String title, String link, String desc) {
      NewsArticle nw = new NewsArticle();
      nw.setTitle(title);
      nw.setOriginallink(link);
      nw.setDescription(desc);
      this.newsRepository.save(nw);
   }

   private  String get(String apiUrl, Map<String, String> requestHeaders) {
      HttpURLConnection con = connect(apiUrl);
      try {
         con.setRequestMethod("GET");
         for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
            con.setRequestProperty(header.getKey(), header.getValue());
         }

         int responseCode = con.getResponseCode();
         if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
            return readBody(con.getInputStream());
         } else { // 오류 발생
            return readBody(con.getErrorStream());
         }
      } catch (IOException e) {
         throw new RuntimeException("API 요청과 응답 실패", e);
      } finally {
         con.disconnect();
      }
   }

   private  HttpURLConnection connect(String apiUrl) {
      try {
         URL url = new URL(apiUrl);
         return (HttpURLConnection) url.openConnection();
      } catch (MalformedURLException e) {
         throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
      } catch (IOException e) {
         throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
      }

   }

   private  String readBody(InputStream body) throws UnsupportedEncodingException {
      InputStreamReader streamReader = new InputStreamReader(body, "UTF-8");

      try (BufferedReader lineReader = new BufferedReader(streamReader)) {
         StringBuilder responseBody = new StringBuilder();

         String line;
         while ((line = lineReader.readLine()) != null) {
            responseBody.append(line);
         }

         return responseBody.toString();
      } catch (IOException e) {
         throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
      }
   }
   public List<NewsArticle> getAllNewsArticles() {
       return newsRepository.findAll();
   }
}
