from flask import current_app
from flask_sqlalchemy import SQLAlchemy
from itsdangerous import TimedJSONWebSignatureSerializer, BadSignature, SignatureExpired
from sqlalchemy import Column, String, Boolean, Integer, ForeignKey
from sqlalchemy.orm import relationship

db = SQLAlchemy()


class User(db.Model):
    __tablename__ = 'user'

    id = Column(Integer, primary_key=True)
    username = Column(String, nullable=False)
    password_hash = Column(String, nullable=False)

    question_stats = relationship('QuestionStats', back_populates='user')

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
    country_code = Column(String, ForeignKey('country.code'), nullable=False)
    text = Column(String, nullable=False)

    answers = relationship('Answer', back_populates='question')
    country = relationship('Country')


class Answer(db.Model):
    __tablename__ = 'answer'

    id = Column(Integer, primary_key=True)
    text = Column(String, nullable=False)
    correct = Column(Boolean, nullable=False)
    question_id = Column(Integer, ForeignKey('question.id'), nullable=False)

    question = relationship('Question', back_populates='answers')


class QuestionStats(db.Model):
    __tablename__ = 'question_stats'

    user_id = Column(Integer, ForeignKey('user.id'), primary_key=True)
    country_code = Column(String, ForeignKey('country.code'), primary_key=True)

    questions_answered = Column(Integer, nullable=False)
    questions_correct = Column(Integer, nullable=False)

    user = relationship('User', back_populates='question_stats')


class QuestionProgress(db.Model):
    __tablename__ = 'question_progress'

    user_id = Column(Integer, ForeignKey('user.id'), primary_key=True)
    question_id = Column(Integer, ForeignKey('question.id'), primary_key=True)

    user = relationship('User')
    question = relationship('Question')


class Country(db.Model):
    __tablename__ = 'country'

    code = Column(String, primary_key=True)
    name = Column(String, nullable=False)