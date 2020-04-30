#!/bin/bash
./gradlew build
docker build . -t demo-01
docker run -p 8080:8080 demo-01