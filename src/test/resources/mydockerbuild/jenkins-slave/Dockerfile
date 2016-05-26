FROM centos:7
MAINTAINER Maxfield Stewart, Steve Zhou

# Install Essentials
RUN yum update -y && \
    yum clean all

# Install Packages
RUN yum install -y git && \
    yum install -y wget && \
	yum install -y openssh-server && \
	yum install -y java-1.8.0-openjdk-devel && \
	yum install -y sudo && \
	yum clean all

#download maven for maven build
RUN wget http://mirror.cc.columbia.edu/pub/software/apache/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz
RUN tar xzf apache-maven-3.2.5-bin.tar.gz -C /usr/local
RUN ln -s /usr/local/apache-maven-3.2.5 /usr/local/maven
ENV M2_HOME /usr/local/maven
ENV PATH $M2_HOME/bin:$PATH

#change maven local repository to specific folder on volume
RUN mkdir -p /var/maven/localRepo
RUN sed -i '55i<localRepository>/var/maven/localRepo</localRepository>' /usr/local/apache-maven-3.2.5/conf/settings.xml

# gen dummy keys, centos doesn't autogen them like ubuntu does
RUN /usr/bin/ssh-keygen -A

# Set SSH Configuration to allow remote logins without /proc write access
RUN sed -ri 's/^session\s+required\s+pam_loginuid.so$/session optional pam_loginuid.so/' /etc/pam.d/sshd

# Create Jenkins User
RUN useradd jenkins -m -s /bin/bash

# Add public key for Jenkins login
RUN mkdir /home/jenkins/.ssh

COPY /files/authorized_keys /home/jenkins/.ssh/authorized_keys

RUN chown -R jenkins /var/maven/localRepo
RUN chgrp -R jenkins /var/maven/localRepo
RUN chown -R jenkins /home/jenkins
RUN chgrp -R jenkins /home/jenkins
RUN chmod 600 /home/jenkins/.ssh/authorized_keys
RUN chmod 700 /home/jenkins/.ssh

# Add the jenkins user to sudoers
RUN echo "jenkins    ALL=(ALL)    ALL" >> etc/sudoers

# Set Name Servers
COPY /files/resolv.conf /etc/resolv.conf

# Expose SSH port and run SSHD
EXPOSE 22
CMD ["/usr/sbin/sshd","-D"]
