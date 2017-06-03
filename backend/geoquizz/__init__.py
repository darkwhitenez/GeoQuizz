from flask import Flask


def create_app():
    app = Flask(__name__)
    app.config.from_object('config')

    from geoquizz.models import db
    db.init_app(app)
    db.create_all(app=app)

    from geoquizz.api import api
    app.register_blueprint(api, url_prefix='/api')

    return app
