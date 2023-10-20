package com.example.ChatBot.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ChatBot.modal.Conversation;

@RestController
@RequestMapping("/api")
public class ServerService {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@GetMapping("/rasa")
	public Conversation sendToClient(@RequestBody Conversation conversation) {
		String question = conversation.getQuestion();
		try {
			//api rasa
			String apiUrl = "http://localhost:5005/webhooks/rest/webhook";
			//tạo url
			URL url = new URL(apiUrl);
			//mở cổng kết nối
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			
			//thiết lập method 
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			httpURLConnection.setDoOutput(true);
			
			//tạo đối tượng dữ liệu
			JSONObject requestData = new JSONObject();
            requestData.put("message", question);
//            String requestData = "message=" + URLEncoder.encode(question, "UTF-8");
            
            //gửi dữ liệu cho rasa
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(requestData.toString().getBytes("UTF-8"));
            os.flush();
            os.close();
            
            //đọc dữ liệu trả về
			BufferedReader bf = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
            while ((line = bf.readLine()) != null) {
                response.append(line);
            }
            bf.close();
            
            //xử lí kết quả
            String answer = response.toString();
            int startIndex = response.lastIndexOf(",\"text\":\"") + 9;
            int endIndex = response.lastIndexOf("\"}");
			conversation.setAnswer(answer.substring(startIndex, endIndex));
			
			httpURLConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conversation;
	}
}

