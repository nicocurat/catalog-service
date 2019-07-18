# Pull base image
FROM openjdk:11.0.2

# Env variables
ENV SCALA_VERSION 2.12.8
ENV SBT_VERSION 1.2.8

RUN \
  curl -L -o sbt-$SBT_VERSION.deb http://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt && \
  sbt sbtVersion

# Define working directory
WORKDIR /root

ADD src /root/src
ADD project /root/project
ADD build.sbt /root/build.sbt

CMD sbt run
