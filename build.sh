#!/bin/bash

# 1. Point to Java 21
export JAVA_HOME=/usr/lib/jvm/java-21-amazon-corretto
export PATH=$JAVA_HOME/bin:$PATH

# 2. Check version to be sure (will show in logs)
java -version

# 3. Run the Gradle build
chmod +x ./gradlew
./gradlew :composeApp:wasmJsBrowserDistribution