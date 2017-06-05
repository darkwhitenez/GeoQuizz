from gevent import monkey
from gevent.wsgi import WSGIServer

from geoquizz import create_app

monkey.patch_all()

app = create_app()
http_server = WSGIServer(('', 5000), app)
http_server.serve_forever()