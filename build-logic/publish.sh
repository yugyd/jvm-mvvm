#!/bin/bash

# Add sonatype properties to environment variables
# ORG_GRADLE_PROJECT_mavenCentralUsername=username
# ORG_GRADLE_PROJECT_mavenCentralPassword=the_password

# Add signing properties to environment variables
# ORG_GRADLE_PROJECT_signingInMemoryKey=exported_ascii_armored_key
# Optional
# ORG_GRADLE_PROJECT_signingInMemoryKeyId=12345678
# If key was created with a password.
# ORG_GRADLE_PROJECT_signingInMemoryKeyPassword=some_password

./gradlew clean --console=plain
./gradlew publishToMavenCentral --console=plain
