#!/bin/sh

case "$1" in
"deploy")
  ./gradlew clean build
  docker build -t "vollino/poll-service" ./
  docker swarm init
  docker stack deploy -c stack.yml poll-service-stack
  ;;
"rm")
  docker stack rm poll-service-stack
  docker swarm leave -f
  ;;
*)
  echo "Provide an argument 'deploy' or 'rm'"
  ;;
esac
