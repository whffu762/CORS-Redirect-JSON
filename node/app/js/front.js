function makeRequest(url){

    const input = document.getElementById("data").value;
    const result = document.getElementById("result");
    result.innerHTML = "";

    const data = {
        data: input
    }

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    }).then(response => {
        console.log("request url : ", url);
        console.log("response url : ", response.url);
        return response.json();
    }).then(json => {
        result.innerHTML = json.data;
    });
}

const corsBtn1 = document.getElementById("spring-mvcConfigurer");
const corsBtn2 = document.getElementById("spring-annotation");
const corsBtn3 = document.getElementById("spring-header");

const corsBtn4 = document.getElementById("node");
const corsBtn5 = document.getElementById("flask");

corsBtn1.addEventListener("click", function () {

    url = "http://localhost:8080/cors/toSpring-mvcConfigurer";
    makeRequest(url);
})

corsBtn2.addEventListener("click", function(){

    url = "http://localhost:8080/cors/toSpring-annotation";
    makeRequest(url);
})

corsBtn3.addEventListener("click", function () {

    url = "http://localhost:8080/cors/toSpring-header";
    makeRequest(url);
})


corsBtn4.addEventListener("click", function () {

    url = "/toNode"
    makeRequest(url);
})

corsBtn5.addEventListener("click", function () {

    url = "http://localhost:5001/toFlask"
    makeRequest(url);
})

const redirectBtnPost = document.getElementById("redirect-spring-spring-post");
const redirectBtnGet = document.getElementById("redirect-spring-spring-get");
const redirectBtn1 = document.getElementById("redirect-spring-flask");
const redirectBtn2 = document.getElementById("redirect-spring-node");

redirectBtnPost.addEventListener("click", function(){

    url = "http://localhost:8080/redirect/spring-spring";
    makeRequest(url);
})

redirectBtnGet.addEventListener("click", function(){

    const input = document.getElementById("data").value;
    const result = document.getElementById("result");
    result.innerHTML = "";

    url = "http://localhost:8080/redirect/spring-spring?data=" + input;
    fetch(url, {
        method: "GET"
    }).then(response => {
        console.log("request url : ", url);
        console.log("response url : ", response.url);
        return response.json();
    }).then(json => {
        result.innerHTML = json.data;
    });
})

redirectBtn1.addEventListener("click", function(){
    
    url = "http://localhost:8080/redirect/spring-flask";
    makeRequest(url);
});

redirectBtn2.addEventListener("click", function(){

    url = "http://localhost:8080/redirect/spring-node";
    makeRequest(url);
});



const redirectBtn3 = document.getElementById("redirect-spring-flask-model");
const redirectBtn4 = document.getElementById("redirect-spring-flask-addAttribute");
const redirectBtn5 = document.getElementById("redirect-spring-flask-settingCodeAndHeader");

redirectBtn3.addEventListener("click", function(){

    url = "http://localhost:8080/redirect/spring-flask-model";
    makeRequest(url);

});

redirectBtn4.addEventListener("click", function(){

    url = "http://localhost:8080/redirect/spring-flask-addAttribute";
    makeRequest(url);

});

redirectBtn5.addEventListener("click", function(){

    url = "http://localhost:8080/redirect/spring-flask-settingCodeAndHeader";
    makeRequest(url);

});

const redirectBtn6 = document.getElementById("redirect-spring-flask-addFlashAttribute");
const redirectBtn7 = document.getElementById("redirect-spring-spring-addFlashAttribute");

redirectBtn6.addEventListener("click", function(){

    url = "http://localhost:8080/redirect/spring-flask-addFlashAttribute";
    makeRequest(url);

});

redirectBtn7.addEventListener("click", function(){

    url = "http://localhost:8080/redirect/spring-spring-addFlashAttribute";
    makeRequest(url);

});



const jsonBtn1 = document.getElementById("toObject-objectMapper");
const jsonBtn2 = document.getElementById("toJSON-objectMapper");
const jsonBtn3 = document.getElementById("toJSON-responseBody");
const jsonBtn4 = document.getElementById("toJSON-responseEntity");

jsonBtn1.addEventListener("click", function(){

    url = "http://localhost:8080/json/toObject-objectMapper";
    makeRequest(url);
})

jsonBtn2.addEventListener("click", function(){

    url = "http://localhost:8080/json/toJSON-objectMapper";
    makeRequest(url);
})

jsonBtn3.addEventListener("click", function(){

    url = "http://localhost:8080/json/toJSON-responseBody";
    makeRequest(url);
})

jsonBtn4.addEventListener("click", function(){

    url = "http://localhost:8080/json/toJSON-responseEntity";
    makeRequest(url);
})