from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient
from datetime import datetime

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.dbStock


@app.route('/')
def index():
    articles = list(db.articles.find({}, {'_id': False}).sort('reg_date', -1))
    return render_template('index.html', articles=articles)


@app.route('/post', methods=['POST'])
def save_post():
    url_receive = request.form['url_give']
    comment_receive = request.form['comment_give']
    articles = list(db.articles.find({}))
    idx_receive = len(articles)+1

    today = datetime.now()
    uploadtime = today.strftime("%Y-%m-%d-%H-%M-%S")


    doc = {
        'idx': idx_receive,
        'title': url_receive,
        'content': comment_receive,
        'reg_date': uploadtime,

    }

    db.articles.insert_one(doc)

    return jsonify({"result": "success",'msg':'저장 성공!'})



@app.route('/post', methods=['DELETE'])
def delete_post():
    idx_receive = request.args.get('idx_give')
    db.articles.delete_one({'idx': int(idx_receive)})

    return jsonify({'result': 'success', 'msg': '삭제 성공!'})


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)