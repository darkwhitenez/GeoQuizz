from flask import current_app
from flask_sqlalchemy import SQLAlchemy
from itsdangerous import TimedJSONWebSignatureSerializer, BadSignature, SignatureExpired
from sqlalchemy import Column, String, Boolean, Integer, ForeignKey

db = SQLAlchemy()


class User(db.Model):
    __tablename__ = 'user'

    id = Column(Integer, primary_key=True)
    username = Column(String, nullable=False)
    password_hash = Column(String, nullable=False)

    questions_answered = Column(Integer, nullable=False, default=0)
    questions_correct = Column(Integer, nullable=False, default=0)

    def generate_token(self, expire_seconds=60*60):
        s = TimedJSONWebSignatureSerializer(current_app.secret_key, expires_in=expire_seconds)
        return s.dumps(self.id).decode()

    @staticmethod
    def verify_token(token):
        s = TimedJSONWebSignatureSerializer(current_app.secret_key)
        try:
            user_id = s.loads(token)
        except (BadSignature, SignatureExpired):
            return None
        return User.query.get(user_id)


class Question(db.Model):
    __tablename__ = 'question'

    id = Column(Integer, primary_key=True)
    country = Column(String, nullable= False)
    text = Column(String, nullable=False)


class Answer(db.Model):
    __tablename__ = 'answer'

    id = Column(Integer, primary_key=True)
    text = Column(String, nullable=False)
    correct = Column(Boolean, nullable=False)

    question_id = Column(Integer, ForeignKey('question.id'), nullable=False)