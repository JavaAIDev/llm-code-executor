#!/usr/bin/env just --justfile

build:
    mvn -B -ntp -DskipTests package

[working-directory('container-image-generator')]
generatePythonContainerImages: build
    java -jar target/container-image-generator.jar python \
      profiles/python/base-3.12.json --output=container-images/python/base-3.12

[working-directory('container-image-generator')]
generateJavaContainerImages: build
    java -jar target/container-image-generator.jar java \
          profiles/java/base-21.json --output=container-images/java/base-21
