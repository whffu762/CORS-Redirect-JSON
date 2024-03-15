package com.example.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Controller
@CrossOrigin(origins = { "http://localhost:5000", "null"})
public class CorsController {

    @PostMapping("/toSpring")
    public ResponseEntity<String> spring(@RequestBody Map<String, String> jsonMap) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(jsonMap);
        return ResponseEntity.ok().body(result);
    }

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

    @PostMapping("/spring-flask-addAttribute")
    public String redirectAddAttribute(@RequestBody Map<String, String> jsonMap, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("data", jsonMap.get("data"));
        return "redirect:http://localhost:5001/redirectFromSpring";
    }

    //Spring 세션에 저장하는 addFlashAttribute
    @PostMapping("/spring-flask-addFlashAttribute")
    public String redirectAddFlashAttribute(@RequestBody Map<String, String> jsonMap, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("data", jsonMap);
        return "redirect:http://localhost:5001/redirectaddFlashAttributeFromSpring";
    }

}
