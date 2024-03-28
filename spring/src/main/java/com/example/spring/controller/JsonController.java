package com.example.spring.controller;

import com.example.spring.TestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/json")
@Slf4j
public class JsonController {

    /*
        Json String -> Json Object (역직렬화) : ObjectMapper 이용
        하지만 대부분 @RequestBody 를 통함
     */
    @PostMapping("/toObject-objectMapper")
    public ResponseEntity<Map<String, String>> spring2(RequestEntity<String> requestEntity) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String body = requestEntity.getBody();
        Map<String, String> jsonMap = objectMapper.readValue(body, Map.class);

        return ResponseEntity.ok().body(jsonMap);
    }

    /*
        Json Object -> Json String (직렬화) : ObjectMapper 이용
        하지만 대부분 @ResponseBody, @RestController, ResponseEntity<> 를 이용함
     */
    @PostMapping("/toJSON-objectMapper")
    public ResponseEntity<String> spring2(@RequestBody Map<String, String> jsonMap) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(jsonMap);

        return ResponseEntity.ok().body(result);
    }
    
    /*
        Json Object -> Json String (직렬화) : @ResponseBody 이용
        직렬화 뿐만 아니라 반환값을 알아서 Body 에 실어줌
     */
    @ResponseBody
    @PostMapping("/toJSON-responseBody")
    public TestData testData(@RequestBody TestData testData){
        
        return testData;
    }

    /*
        Json Object -> Json String (역직렬화) : ResponseEntity<> 이용
        ResponseEntity<> 는 Map<>, TestData 같은 개발자가 구현한 클래스도 받을 수 있음
     */
    @PostMapping("/toJSON-responseEntity")
    public ResponseEntity<Map<String, String>> spring(@RequestBody Map<String, String> jsonMap) {

        return ResponseEntity.ok().body(jsonMap);
    }
}
