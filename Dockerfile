FROM openjdk:11-jdk
MAINTAINER Stefan Angelov <cefothe@gmail.com>

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH=$PATH:$JAVA_HOME/bin

# These ENV vars can be overridden in docker-compose.yml
ENV JAR_FILE=app.jar
ENV DEBUG_MICROSERVICE=false

# Set timezone to Europe/Sofia
RUN echo Europe/Sofia > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata

COPY ./docker/scripts/docker-entrypoint.sh /

RUN chmod +x /docker-entrypoint.sh
ENV JAVA_OPTS=""

CMD ["/docker-entrypoint.sh"]
