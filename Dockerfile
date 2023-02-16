FROM jenkins/jenkins:lts
USER root
RUN apt-get update && apt-get install -y ca-certificates curl gnupg lsb-release && \
    curl -fsSL https://get.docker.com -o get-docker.sh && sh get-docker.sh && \
    usermod -a -G docker jenkins
USER jenkins
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
COPY jenkins/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
COPY images /images
COPY jenkins /jenkins
ENV DOCKER_HOST tcp://dind:2375