package com.example.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Controller
@Slf4j
//@CrossOrigin(origins = { "http://localhost:5000", "null"}, maxAge = 5000)
public class CorsController {

    @PostMapping("/toSpring")
    public ResponseEntity<String> spring(@RequestBody Map<String, String> jsonMap) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(jsonMap);

        return ResponseEntity.ok().body(result);
    }

    //헤더에 직접 Access-control-allow-origin 항목을 추가해도 됨
    @PostMapping("/toSpring2")
    public ResponseEntity<String> spring2(@RequestBody Map<String, String> jsonMap) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Control-Allow-Origin", "http://localhost:5000");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(jsonMap);

        return ResponseEntity.ok().headers(headers).body(result);
    }


    //redirect 접두사를 이용하면 GET 밖에 안 됨 자세한건 노션 참고
    @PostMapping("/redirect-spring-flask")
    public String redirectToFlask(@RequestBody Map<String, String> jsonMap) {

        String data = URLEncoder.encode(jsonMap.get("data"), StandardCharsets.UTF_8);
        return "redirect:http://localhost:5001/redirectFromSpring?data=" + data;
    }

    @PostMapping("/redirect-spring-node")
    public String redirectToNode(@RequestBody Map<String, String> jsonMap){

        String data = URLEncoder.encode(jsonMap.get("data"), StandardCharsets.UTF_8);
        return "redirect:http://localhost:5000/redirectFromSpring?data=" + data;
    }

    //Spring Boot 에선 이 기능이 안된다고 함 Spring 에선 되는 듯? 400 에러 나는게 정상 이 역할을 RedirectAttributes 가 대신함
    @PostMapping("/spring-flask-model")
    public String redirectModel(@RequestBody Map<String, String> jsonMap, Model model){

        for(String key : jsonMap.keySet()){
            model.addAttribute(key, jsonMap.get(key));
        }

        return "redirect:/modelFromSpring";
    }

    //addAttribute 를 이용하면 쿼리 파라미터를 알아서 만들어줌
    @PostMapping("/spring-flask-addAttribute")
    public String redirectAddAttribute(@RequestBody Map<String, String> jsonMap, RedirectAttributes redirectAttributes) {

        for(String key : jsonMap.keySet()){
            redirectAttributes.addAttribute(key, jsonMap.get(key));
        }

        return "redirect:http://localhost:5001/redirectFromSpring";
    }

    //addFlashAttribute 를 이용하면 스프링 세션을 이용해 body 데이터 전달이 가능
    @PostMapping("/spring-spring-addFlashAttribute")
    public String redirectAddFlashAttribute(@RequestBody Map<String, String> jsonMap, RedirectAttributes redirectAttributes){

        for(String key : jsonMap.keySet()){

            log.info("testData : {} {}", key, jsonMap.get(key));
            redirectAttributes.addFlashAttribute(key, jsonMap.get(key));
        }

        return "redirect:/fromSpringToSpring";
    }

    //Spring 세션에 저장하는 addFlashAttribute 
    //flask 같은 Spring 이 아닌 다른 서버로 전송될 땐 동작하지 않는게 정상임
    @PostMapping("/spring-flask-addFlashAttribute")
    public String redirectAddFlashAttribute2(@RequestBody Map<String, String> jsonMap, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute(jsonMap);
        return "redirect:http://localhost:5001/redirectAddFlashAttributeFromSpring";
    }

    //redirect 접두사 말고 직접 상태 코드와 location 헤더를 설정하면 원하는 redirect 를 사용할 수 있음
    @PostMapping("/redirect-spring-flask2")
    public ResponseEntity<String> redirectToFlask2(@RequestBody Map<String, String> jsonMap, RedirectAttributes redirectAttributes) throws URISyntaxException {

        String data = URLEncoder.encode(jsonMap.get("data"), StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("http://localhost:5001/toFlask"));

        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).headers(headers).body(data);
    }
}
