FROM jenkins/jenkins:lts-jdk11
USER root
RUN apt-get update && apt-get install -y ca-certificates curl gnupg lsb-release wget && \
    curl -fsSL https://get.docker.com -o get-docker.sh && sh get-docker.sh && \
    usermod -a -G docker jenkins && \
    curl -sL https://aka.ms/InstallAzureCLIDeb | bash && \
    wget https://github.com/mikefarah/yq/releases/latest/download/yq_linux_amd64 -O /usr/bin/yq && \
    chmod +x /usr/bin/yq
USER jenkins
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
COPY --chown=jenkins:jenkins jenkins/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt
COPY images /images
COPY jenkins /jenkins
ENV DOCKER_HOST tcp://dind:2375
EXPOSE 8080