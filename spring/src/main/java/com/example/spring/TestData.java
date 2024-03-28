package com.example.spring;

import lombok.Getter;

@Getter
public class TestData {

    private String data;

    /*
        주의
        개발자가 구현한 클래스를 @ResponseBody 등 ObjectMapper 를 이용해서 변환하면
        get 으로 시작하는 모든 메소드에 대해 JSON 문자열로 변환 함
     */
    public String getHello(){
        return "hello";
    }
}
