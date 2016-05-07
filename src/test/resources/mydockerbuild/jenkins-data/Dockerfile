FROM debian:jessie
MAINTAINER  STEVE.ZHOU

RUN useradd -d "/var/jenkins_home" -u 1000 -m -s /bin/bash jenkins
RUN mkdir -p /var/log/jenkins
RUN mkdir -p /var/maven/localRepo
RUN chown -R jenkins:jenkins /var/log/jenkins

RUN chown -R jenkins:jenkins /var/maven/localRepo

VOLUME ["/var/log/jenkins","/var/jenkins_home","/var/maven/localRepo"]

USER jenkins

CMD ["echo", "Data container for Jenkins"]

