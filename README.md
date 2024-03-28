# CORS, Redirect, JSON 실험

node(5000) : front 서버

flask(5001) : backend 서버1

spring(8080) : backend 서버2


## 요청 흐름

### CORS

http://localhost:8080/cors/toSpring-mvcConfigurer : node -> spring : CORS 전역 설정 적용

http://localhost:8080/cors/toSpring-annotation : node -> spring : CORS 어노테이션 적용

http://localhost:8080/cors/toSpring-header : node -> spring : CORS 헤더 직접 설정

htp://localhost:5000/toNode : node -> node

http://localhost:5001/toFlask : node -> flask



### Redirect

http://localhost:8080/redirect/spring-flask : node -> spring -> node -> flask : "redirect:" 접두사 이용

http://localhost:8080/redirect/spring-node : node -> spring -> node(server.js) -> node(front.js) : "redirect:" 접두사 이용

http://localhost:8080/redirect/spring-flask-addAttribute : spring -> flask : RedirectAttributes를 이용한 쿼리 파라미터 간편화

http://localhost:8080/redirect/spring-flask-settingCodeAndHeader : spring -> flask : 상태코드와 Location 헤더 직접 설정


#### 정상 동작하지 않는게 정상

http://localhost:8080/redirect/spring-flask-model : spring -> flask : model을 이용한 쿼리 파라미터 간편화(Spring Boot에선 동작 안함)

http://localhost:8080/redirect/spring-flask-addFlashAttribute : spring -> flask : Spring 세션을 통한 데이터 전달(Spring 끼리만 됨)


#### 이건 왜 안 되지?

http://localhost:8080/redirect/spring-spring-addFlashAttribute : spring -> spring : 왜 안되는 걸까 값이 null만 들어감




### JSON

http://localhost:8080/json/toObject-objectMapper : JSON -> Object (역직렬화) : ObjectMapper.readValue 이용

http://localhost:8080/json/toJSON-objectMapper : Object -> JSON (직렬화) : ObjectMapper.writeValueAsString 이용

http://localhost:8080/json/toJSON-responseBody : Object -> JSON (직렬화) : @ResponseBody 어노테이션 이용

http://localhost:8080/json/toJSON-responseEntity : Object -> JSON (직렬화) : ResponseEntity<> 이용