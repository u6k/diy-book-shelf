FROM python:latest
LABEL maintainer="u6k.apps@gmail.com"

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get clean && \
    pip install pipenv

WORKDIR /var/myapp
VOLUME /var/myapp
