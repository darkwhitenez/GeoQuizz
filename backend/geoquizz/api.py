import random
from functools import wraps

from flask import Blueprint, request, jsonify, g
from werkzeug.security import generate_password_hash, check_password_hash

from geoquizz.models import db, User

api = Blueprint('api', __name__)


def authenticate(f):
    @wraps(f)
    def wrapper(*args, **kwargs):
        token = request.headers.get('X-Auth-Token', '')
        user = User.verify_token(token)
        if user:
            g.user = user
        else:
            return jsonify(success=False, error='invalid_token'), 401
        return f(*args, **kwargs)
    return wrapper


@api.route('/user/login', methods=['POST'])
def login():
    username = request.form['username']
    password = request.form['password']

    user = User.query.get(username)
    if user and check_password_hash(user.password_hash, password):
        token = user.generate_token()
        return jsonify(success=True, token=token)

    return jsonify(success=False, error='invalid_login'), 401


@api.route('/user/register', methods=['POST'])
def register():
    username = request.form['username']
    password = request.form['password']

    if User.query.get(username):
        return jsonify(success=False, error='already_exists'), 409

    password_hash = generate_password_hash(password)
    db.session.add(User(username=username, password_hash=password_hash))
    db.session.commit()
    return jsonify(success=True)


@api.route('/quiz/get_random')
@authenticate
def hello_world():
    answers = [{'text': f'Dolor sit amet {i}', 'correct': False} for i in range(4)]
    random.choice(answers)['correct'] = True
    return jsonify(question={'text': 'Lorem ipsum'}, answers=answers)