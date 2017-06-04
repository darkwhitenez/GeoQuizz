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
        from geoquizz.models import db, Country, Question, Answer
        db.create_all()

        if not Country.query.first():
            from geoquizz.seed.country_data import countries
            for code, name in countries.items():
                db.session.add(Country(code=code, name=name))
                db.session.commit()

        if not Question.query.first():
            from geoquizz.seed.question_data import questions
            for question_dict in questions:
                answers = [
                    Answer(text=answer_dict['text'], correct=answer_dict['correct'])
                    for answer_dict in question_dict['answers']
                ]
                question = Question(text=question_dict['text'],
                                    answers=answers,
                                    country_code=question_dict['country'])
                db.session.add(question)
            db.session.commit()