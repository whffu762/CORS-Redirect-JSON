const express = require("express");

const app = express();
const port = 5000;

const cors = require('cors');

app.use(express.json());
app.use(cors({
    origin: "null",
    optionsSuccessStatus: 200
}));

app.get("/", (req, resp) =>{
    resp.sendFile(__dirname + '/app/html/front.html');
});

app.get("/getfrontJS", (req, resp) =>{
    resp.sendFile(__dirname + '/app/js/front.js');
})

app.post("/toNode", (req, resp) =>{

    var result = req.body;
    resp.send(result);
});

app.get("/redirectFromSpring", (req, resp) =>{

    var data = req.query.data;
    var json = JSON.stringify({"data" : data})

    resp.send(json);
})



app.listen(port, ()=>{
    console.log("서버 실행");
});
