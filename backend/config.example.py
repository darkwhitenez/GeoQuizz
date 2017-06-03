import os

DEBUG = True
SQLITE = True

SQLALCHEMY_TRACK_MODIFICATIONS = False

if SQLITE:
    config_dir = os.path.abspath(os.path.dirname(__file__))
    SQLALCHEMY_DATABASE_URI = 'sqlite:///' + os.path.join(config_dir, 'db.sqlite')
else:
    SQLALCHEMY_DATABASE_URI = 'postgres:///user:pass@localhost/geoquizz'
SECRET_KEY = 'secret'
