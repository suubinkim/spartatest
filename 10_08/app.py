from datetime import datetime

from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.dbStock


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/post', methods=['POST'])
def save_post():
    title = request.form.get('title')
    content = request.form.get('content')
    post_count = db.post.count()
    if post_count == 0:
        max_value = 1
    else:
        max_value = db.post.find_one(sort=[("idx", -1)])['idx'] + 1

    post = {
        'idx': max_value,
        'title': title,
        'content': content,
        'reg_date': datetime.now(),
        'read_count': 0
    }
    db.post.insert_one(post)
    return {"result": "success"}


@app.route('/posts', methods=['GET'])
def get_posts():
    posts = list(db.post.find({}, {'_id': False}).sort([("reg_date", -1)]))
    for a in posts:
        a['reg_date'] = a['reg_date'].strftime('%Y.%m.%d %H:%M:%S')

    return jsonify({"posts": posts})

@app.route('/post', methods=['GET'])
def get_post():
    idx = request.args['idx']
    posts = db.post.find_one({'idx': int(idx)}, {'_id': False})
    return jsonify({"posts": posts})


@app.route('/post/<idx>', methods=['PUT'])
def read_post(idx):
    db.post.update_one({'idx': int(idx)}, {'$inc': {'read_count': 1}})
    posts = db.post.find_one({'idx': int(idx)}, {'_id': False})
    return jsonify({"posts": posts})

@app.route('/post', methods=['PUT'])
def update_post():
    title = request.form.get('title')
    content = request.form.get('content')
    idx = request.form.get('idx')
    db.post.update_one({'idx': int(idx)},  {'$set': {'title': title, 'content': content}})
    return {"result": "success"}

@app.route('/post', methods=['DELETE'])
def delete_post():
    idx = request.args.get('idx')
    db.post.delete_one({'idx': int(idx)})
    return {"result": "success"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)