cors에 대한 실험

node(5000) : front 서버
flask(5001) : backend 서버1
spring(8080) : backedn 서버2


요청 흐름
http://localhost:8080/toSpring : node -> spring

http://localhost:5001/toFlask : node -> flask



http://localhost:8080/redirect-spring-flask : node -> spring -> node -> flask
spring에서 node에게 flask redirect로 응답함
redirect시 쿼리 파라미터를 이용해 이전 요청의 데이터를 전달할 수 있음   


http://localhost:8080/redirect-spring-node : node -> spring -> node(server.js) -> node(front.js) 
spring에서 node의 router로 redirect하라고 응답함



http://localhost:8080/spring-flask-addAttribute : node -> spring -> node -> flask
위 flask redirect와 동일하지만 Spring의 RedirectAttribute.addAttribute()를 이용
쿼리 파라미터를 알아서 만들어줌

http://localhost:8080/spring-flask-addFlashAttribute : node -> spring -> flask
flask redirect와 동일하지만 Spring의 RedirectAttribute.addFlashAttribute()를 이용
Spring 세션을 이용하기 때문에 다른 서버로의 이동은 불가함