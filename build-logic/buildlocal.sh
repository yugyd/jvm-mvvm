#!/bin/bash

# Add signing properties SIGNING_KEY_ID, SIGNING_KEY, SIGNING_PASSWORD to environment variables
./gradlew :jvmmvvm-ui:publishToMavenLocal --console=plain
./gradlew :jvmmvvm-domain:publishToMavenLocal --console=plain
./gradlew :jvmmvvm-bom:publishToMavenLocal --console=plain
