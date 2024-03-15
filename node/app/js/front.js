const btn1 = document.getElementById("spring");
const btn2 = document.getElementById("node");
const btn3 = document.getElementById("flask");

btn1.addEventListener("click", function () {

    url = "http://localhost:8080/toSpring";
    makeRequest(url);
})

btn2.addEventListener("click", function(){

    url = "/toNode"
    makeRequest(url);
})

btn3.addEventListener("click", function () {

    url = "http://localhost:5001/toFlask";
    makeRequest(url);
})

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
        console.log("응답이 왔음");
        return response.json();
    }).then(json => {
        result.innerHTML = json.data;
    });
}

const redirectBtn1 = document.getElementById("redirect-spring-flask");
const redirectBtn2 = document.getElementById("redirect-spring-node");

redirectBtn1.addEventListener("click", function(){
    url = "http://localhost:8080/redirect-spring-flask";
    makeRequest(url);
});

redirectBtn2.addEventListener("click", function(){

    url = "http://localhost:8080/redirect-spring-node";
    makeRequest(url);
});


const rediretModelBtn = document.getElementById("addAttr");
const redirectAttributeBtn = document.getElementById("addFlashAttr");

rediretModelBtn.addEventListener("click", function(){
    url = "http://localhost:8080/spring-flask-addAttribute";
    makeRequest(url);
})


redirectAttributeBtn.addEventListener("click", function(){
    url = "http://localhost:8080/spring-flask-addFlashAttribute";
    makeRequest(url);
})