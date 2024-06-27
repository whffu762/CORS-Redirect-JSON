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
@RequestMapping("/redirect")
@Slf4j
public class RedirectController {

    /*
        redirect 되면 GET 메소드로 변경됨
        또한 URL encoding 또한 다시 해야 함
     */
    @GetMapping("/fromSpring")
    public String redirectFromSpring(@RequestParam("data") String data) {

        String tmp = URLEncoder.encode(data, StandardCharsets.UTF_8);

        return "redirect:http://localhost:5000/redirectFromSpring?data="+tmp;
    }

    @PostMapping("/fromSpring")
    public void redirectFromSpring2(@RequestBody String data){

        log.info("post 호출");

    }
    /*
        Spring 이 POST 로 받은 요청을 redirect
     */
    @PostMapping("/spring-spring")
    public String redirectToSpringPost(@RequestBody Map<String, String> jsonMap){

        String data = URLEncoder.encode(jsonMap.get("data"), StandardCharsets.UTF_8);
        return "redirect:/redirect/fromSpring?data=" + data;
    }

    /*
        Spring 이 GET 으로 받은 요청을 Redirect

        이걸 만든 이유는 Spring Security 에서의 동작 때문임
        로그인이 필요한 특정 기능을 사용할 때 로그인 하지 않은 요청에 대해선 로그인을 유도했다가 다시 원래 페이지로 돌아오는 동작이 이루어져야 함
        자세한 설명은 redirect 노션 참고
     */
    @GetMapping("/spring-spring")
    public String redirectToSpringGet(@RequestParam("data") String data){

        String tmp = URLEncoder.encode(data, StandardCharsets.UTF_8);
        return "redirect:/redirect/fromSpring?data=" + tmp;
    }


    //redirect 접두사를 이용하면 GET 밖에 안 됨 자세한건 노션 참고
    @PostMapping("/spring-flask")
    public String redirectToFlask(@RequestBody Map<String, String> jsonMap) {

        String data = URLEncoder.encode(jsonMap.get("data"), StandardCharsets.UTF_8);
        return "redirect:http://localhost:5001/redirectFromSpring?data=" + data;
    }

    @PostMapping("/spring-node")
    public String redirectToNode(@RequestBody Map<String, String> jsonMap){

        String data = URLEncoder.encode(jsonMap.get("data"), StandardCharsets.UTF_8);
        return "redirect:http://localhost:5000/redirectFromSpring?data=" + data;
    }

    /*
        400 에러 나는게 정상
        Spring Boot 에선 이 기능이 안된다고 함 Spring 에선 되는 듯? 이 역할을 RedirectAttributes 가 대신함
    */
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

    /*
        400 에러 나는게 정상
        Spring 세션에 저장하는 방식이기 때문에 flask 같은 Spring 이 아닌 다른 서버로의 전송은 불가능
     */
    @PostMapping("/spring-flask-addFlashAttribute")
    public String redirectAddFlashAttribute2(@RequestBody Map<String, String> jsonMap, RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute(jsonMap);
        return "redirect:http://localhost:5001/redirectAddFlashAttributeFromSpring";
    }

    /*
        이건 Spring 으로 보내는 건데 왜 안 되지?
        요청은 정상적으로 호출이 되는데 값이 Null 만 들어감
    */
    @PostMapping("/spring-spring-addFlashAttribute")
    public String redirectAddFlashAttribute(@RequestBody Map<String, String> jsonMap, RedirectAttributes redirectAttributes){

        for(String key : jsonMap.keySet()){

            log.info("testData : {} {}", key, jsonMap.get(key));
            redirectAttributes.addFlashAttribute(key, jsonMap.get(key));
        }

        return "redirect:/fromSpringToSpring";
    }

    /*
        redirect 방식을 직접 선택
        상태 코드와 location 헤더를 설정하면 원하는 redirect 를 사용할 수 있음
    */
    @PostMapping("/spring-flask-settingCodeAndHeader")
    public ResponseEntity<String> redirectToFlask2(@RequestBody Map<String, String> jsonMap) throws JsonProcessingException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI("http://localhost:5001/toFlask"));

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(jsonMap);

        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).headers(headers).body(result);
    }

}
