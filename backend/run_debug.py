from geoquizz import create_app

app = create_app()
app.run(host=app.config['HOST'], port=app.config['PORT'], debug=True)
