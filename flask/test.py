from flask import Flask, jsonify, request, session
import logging
from flask_cors import CORS

app = Flask(__name__)
CORS(app, resources={r'*':{ 'origins' : { 'http://localhost:5000', 'null'}}})


@app.route("/toFlask", methods =['post'])
def testCORS() :
    
    body = request.get_json()
    logging.info(body)
    
    return jsonify(body)

@app.route("/redirectFromSpring", methods = ['GET'])
def redirectFromSpring() :
    
    data = request.args.get('data', "data is null")
    json = {'data': data}
    logging.info(json)
    
    return jsonify(json)

#이 요청은 아무런 동작이 없는게 정상
@app.route("/redirectaddFlashAttributeFromSpring", methods = ['GET'])
def redirectFromSpring2() :
    
    data = session["data"]
    logging.info(data)
    
    return jsonify({'data' : data})


if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port = 5001)

