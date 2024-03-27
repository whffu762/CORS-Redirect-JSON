package com.example.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
//@CrossOrigin(origins = {"http://localhost:5000", "null"})
public class testController {

    //아무것도 출력되지 않는게 정상 로그 보면 data 가 비어있음
    @GetMapping("/fromSpringToSpring")
    public ResponseEntity<String> testAddFlashAttributes(@ModelAttribute("data") String data) throws JsonProcessingException {

        log.info("data : {}", data);
        
        Map<String, String> tmp = new HashMap<>();
        tmp.put("data", data);

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(tmp);

        return ResponseEntity.ok().body(result);
    }

    //에러 나는게 정상 데이터를 model 에 추가했지만 쿼리 파라미터로 바뀌지 않기 때문에 이를 받아올 수 없음
    @GetMapping("/modelFromSpring")
    public void testModelAttribute(@RequestParam("data") String data) {

        log.info("data : {}", data);
    }

}
