from itsdangerous import TimedJSONWebSignatureSerializer, BadSignature, SignatureExpired
from sqlalchemy import Column, String, Boolean, Integer, ForeignKey

from geoquizz import app, db


class User(db.Model):
    __tablename__ = 'user'

    username = Column(String, primary_key=True)
    password_hash = Column(String, nullable=False)

    def generate_token(self, expire_seconds=60*60):
        s = TimedJSONWebSignatureSerializer(app.secret_key, expires_in=expire_seconds)
        return s.dumps(self.username).decode()

    @staticmethod
    def verify_token(token):
        s = TimedJSONWebSignatureSerializer(app.secret_key)
        try:
            username = s.loads(token)
        except (BadSignature, SignatureExpired):
            return None
        return User.query.get(username)


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
