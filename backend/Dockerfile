FROM alpine:3.6

ADD . /geoquizz
WORKDIR /geoquizz

RUN apk add --no-cache python3 py3-gevent \
    && python3 -m ensurepip \
    && python3 -m pip install --no-cache-dir -r requirements.txt

EXPOSE 5000

ENTRYPOINT ["python3"]
CMD ["run_gevent.py"]"