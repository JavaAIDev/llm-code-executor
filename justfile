#!/usr/bin/env just --justfile

build:
    mvn -B -ntp -DskipTests package

[working-directory('container-image-generator')]
generateContainerImages: build
    java -jar target/container-image-generator.jar python \
      profiles/python/base-3.12.json --output=container-images/python/base-3.12
