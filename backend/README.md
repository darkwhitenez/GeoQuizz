# GeoQuizz backend

Powering the server-side of GeoQuizz.

## Requirements

* Python 3.6
* SQLAlchemy
* Flask
* Flask-SQLAlchemy
* gevent (for production)

## Installing

### From source

1. Clone the repository

        git clone https://github.com/darkwhitenez/GeoQuizz.git
        cd GeoQuizz

2. Install the requirements

        pip install -r requirements.txt

3. Copy the example configuration

        cp config.example.py config.py

4. Modify the config as necessary. The example configuration will work but you should configure the database uri and the secret key

5. Run

        python run_gevent.py

   or

        python run_debug.py

### With Docker

1. Clone the repository

        git clone https://github.com/darkwhitenez/GeoQuizz.git
        cd GeoQuizz

2. Build the image

        docker build . -t geoquizz

3. Run the container

        docker run -p 5000:5000 -v /path/to/config.py:/geoquizz/config.py geoquizz