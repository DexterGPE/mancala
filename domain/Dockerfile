# Start with an official Ubuntu base image
FROM ubuntu:20.04

# Set environment variables to avoid prompts during installation
ENV DEBIAN_FRONTEND=noninteractive

# Install necessary packages
RUN apt-get update && apt-get install -y \
  curl \
  gnupg \
  openjdk-21-jdk \
  nodejs \
  npm \
  git \
  unzip \
  zip \
  wget \
  lsb-release \
  dos2unix

# Install SDKMAN! and Gradle
RUN curl -s "https://get.sdkman.io" | bash \
&& /bin/bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install gradle" \
&& ln -s $HOME/.sdkman/candidates/gradle/current/bin/gradle /usr/local/bin/gradle

# Set up the backend (Java build)
RUN /bin/bash -c "source $HOME/.sdkman/bin/sdkman-init.sh"

# Copy the backend code into the container
COPY ./domain /mancala/domain
COPY ./api /mancala/api
COPY ./persistence /mancala/persistence
COPY ./client /mancala/client
COPY ./gradle /mancala/gradle
COPY ./settings.gradle /mancala/
COPY ./gradle /mancala/


# Set working directory for all subsequent commands
WORKDIR /mancala

# Set up the backend (Java build)
RUN gradle wrapper
RUN chmod +x gradlew
# Expose backend port
EXPOSE 8080

# Run the backend with gradlew
CMD ["sh", "-c", "pwd && ./gradlew run"]

