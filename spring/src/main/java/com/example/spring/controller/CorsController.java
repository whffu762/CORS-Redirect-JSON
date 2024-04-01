package com.example.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/cors")
@Slf4j

public class CorsController {

    /*
        WebMvcConfigurer 를 이용한 방식
        전역으로 설정됨
     */
    @PostMapping("/toSpring-mvcConfigurer")
    public ResponseEntity<String> withWebMvcConfigurer(@RequestBody Map<String, String> jsonMap) throws JsonProcessingException, InterruptedException {

        log.info("MvcConfigurer 을 통한 컨트롤러 호출");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(jsonMap);

        return ResponseEntity.ok().body(result);
    }

    /*
        어노테이션을 이용한 방식
        지역 설정으로 전역 설정보다 우선순위가 높음
    */
    @PostMapping("/toSpring-annotation")
    @CrossOrigin(origins = { "http://localhost:5000", "null"}, maxAge = 5000)
    public ResponseEntity<String> withAnnotation(@RequestBody Map<String, String> jsonMap) throws JsonProcessingException {

        log.info("어노테이션을 통한 컨트롤러 호출");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(jsonMap);

        return ResponseEntity.ok().body(result);
    }

    /*
        헤더에 직접 Access-control-allow-origin 항목을 추가하는 방식
        전역 설정이 이미 적용되어 있으면 헤더값 여러개라고 에러 발생함
     */
    @PostMapping("/toSpring-header")
    public ResponseEntity<String> withHeader(@RequestBody Map<String, String> jsonMap) throws JsonProcessingException {

        log.info("헤더를 직접 생성하는 컨트롤러 호출");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Access-Control-Allow-Origin", "http://localhost:5000");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(jsonMap);

        return ResponseEntity.ok().headers(headers).body(result);
    }
}
