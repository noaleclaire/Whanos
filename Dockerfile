FROM jenkins/jenkins:lts
USER root
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
COPY jenkins/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
COPY images /images
COPY jenkins /jenkins
RUN mv /jenkins/job_dsl.groovy /var/lib/docker/volumes/jenkins-data/job_dsl.groovy && /var/lib/docker/volumes/jenkins-data/jenkinsConfig.yml 