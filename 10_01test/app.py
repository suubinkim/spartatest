from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient
from datetime import datetime

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.dbStock


@app.route('/')
def index():
    posts = list(db.posts.find({}, {'_id': False}))
    return render_template('index.html', posts=posts)

@app.route('/search', methods=['GET'])
def search_article():
    search_receive = request.args['search']
    articles = db.posts.find({}, {'_id': False})

    search_a = []
    for article in articles:
        if search_receive in article["title"]:
            search_a.append(article)
    return render_template('index.html', posts=search_a)


@app.route('/post', methods=['POST'])
def save_post():
    title_receive = request.form['title_give']
    content_receive = request.form['content_give']
    uploadtime= datetime.now().strftime("%Y.%m.%d %H:%M:%S")
    idx_receive = len(list(db.posts.find({}))) +1

    doc = {
        'idx': idx_receive,
        'title': title_receive,
        'content': content_receive,
        'reg_date': uploadtime,
        'read_count' : 0

    }

    db.posts.insert_one(doc)
    return {"result": "success"}


@app.route('/post', methods=['GET'])
def read_post():
    idx = request.args.get('idx_give')
    posts = db.posts.find_one({'idx': int(idx)},{'_id': False})
    db.posts.update_one({'idx': int(idx)},{'$inc': {'read_count': 1}})
    return jsonify({"posts": posts})

@app.route('/post', methods=['PUT'])
def update_posts():
    idx_receive = request.form['idx_give']
    title_receive = request.form['title_give']
    content_receive = request.form['content_give']
    db.posts.update_one({'idx':int(idx_receive)},{'$set':{'title':title_receive,'content':content_receive}})
    return {"result": "success"}

@app.route('/post', methods=['GET'])
def update_post():
    idx_receive = request.args.get('idx_give')
    posts = db.posts.fine_one({'idx': int(idx_receive)})
    return jsonify({"posts": posts})


@app.route('/post', methods=['DELETE'])
def delete_post():
    idx_receive = request.args.get('idx_give')
    db.posts.delete_one({'idx': int(idx_receive)})
    return {"result": "success"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)