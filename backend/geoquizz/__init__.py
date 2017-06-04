from flask import Flask


def create_app():
    app = Flask(__name__)
    app.config.from_object('config')

    from geoquizz.models import db
    db.init_app(app)
    create_database(app)

    from geoquizz.api import api
    app.register_blueprint(api, url_prefix='/api')

    return app


def create_database(app):
    with app.app_context():
        from geoquizz.models import db, Country
        db.create_all()

        if not Country.query.first():
            from geoquizz.country_data import countries
            for code, name in countries.items():
                db.session.add(Country(code=code, name=name))
                db.session.commit()