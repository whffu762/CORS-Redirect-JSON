package com.example.spring.controller;

import com.example.spring.TestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
public class testController {

    /*
        addFlashAttribute() 를 이용해 redirect 되는 방식
        되야 할 것 같은데 안 됨 로그 보면 data 가 비어있음
     */
    @ResponseBody
    @GetMapping("/fromSpringToSpring")
    public TestData testAddFlashAttributes(@ModelAttribute("data") TestData testData) throws JsonProcessingException {

        log.info("data : {}", testData);

        return testData;
    }

    /*
        에러 나는게 정상
        데이터를 model 에 추가했지만 쿼리 파라미터로 바뀌지 않기 때문에 이를 받아올 수 없음
     */
    @GetMapping("/modelFromSpring")
    public void testModelAttribute(@RequestParam("data") String data) {

        log.info("data : {}", data);
    }

}
