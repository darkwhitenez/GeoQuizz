from functools import wraps

from flask import Blueprint, request, jsonify, g
from sqlalchemy.sql.expression import func
from werkzeug.security import generate_password_hash, check_password_hash

from geoquizz.models import db, User, QuestionStats, Question

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

    user = User.query.filter_by(username=username).first()
    if user and check_password_hash(user.password_hash, password):
        token = user.generate_token()
        return jsonify(success=True, token=token)

    return jsonify(success=False, error='invalid_login'), 401


@api.route('/user/register', methods=['POST'])
def register():
    username = request.form['username']
    password = request.form['password']

    if User.query.filter_by(username=username).first():
        return jsonify(success=False, error='already_exists'), 409

    password_hash = generate_password_hash(password)
    db.session.add(User(username=username, password_hash=password_hash))
    db.session.commit()
    return jsonify(success=True)


@api.route('/quiz/get_random')
@authenticate
def get_random():
    questions = Question.query.order_by(func.random()).limit(7)
    return jsonify(
        [{'text': question.text,
          'answers': [{
              'text': answer.text,
              'correct': answer.correct
           } for answer in question.answers]
          } for question in questions]
    )


@api.route('/quiz/get_for_country', methods=['POST'])
@authenticate
def get_for_country():
    country_code = request.form['country_code']
    questions = Question.query.filter_by(country_code=country_code).order_by(func.random()).limit(7)
    return jsonify(
        [{'text': question.text,
          'answers': [{
              'text': answer.text,
              'correct': answer.correct
           } for answer in question.answers]
          } for question in questions]
    )


@api.route('/quiz/send_result', methods=['POST'])
@authenticate
def send_result():
    country_code = request.form.get('country_code')
    questions_answered = request.form.get('answered', type=int)
    questions_correct = request.form.get('correct', type=int)

    stats = QuestionStats.query.filter_by(country_code=country_code,
                                          user_id=g.user.id).first()

    if not stats:
        stats = QuestionStats(user=g.user,
                              country_code=country_code,
                              questions_answered=questions_answered,
                              questions_correct=questions_correct)
        db.session.add(stats)

    stats.questions_answered += questions_answered
    stats.questions_correct += questions_correct
    db.session.commit()

    return jsonify(success=True)


@api.route('/user/get_stats')
@authenticate
def get_stats():
    stats = [{'questions_answered': s.questions_answered,
              'questions_correct': s.questions_correct,
              'country_code': s.country_code}
             for s in g.user.question_stats]
    return jsonify(stats)