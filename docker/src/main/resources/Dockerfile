FROM tomcat:8.5.33-jre8

# Sets up debugging on Tomcat. If you are debugging an startup issue and need the server to wait for you while you connect, change to suspend=y, but do not check in
RUN echo 'JAVA_OPTS="$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=n"' >> /usr/local/tomcat/bin/setenv.sh

RUN echo 'JAVA_OPTS="$JAVA_OPTS -DKRAUSENING_BASE=/usr/local/tomcat/conf/krausening/base -DKRAUSENING_EXTENSIONS=/usr/local/tomcat/conf/krausening/$CONFIG_ENV -Djava.security.egd=file:/dev/urandom -Ddockerhost=$DOCKERHOST"' >> /usr/local/tomcat/bin/setenv.sh

ADD krausening /usr/local/tomcat/conf/krausening
RUN rm /usr/local/tomcat/conf/logging.properties
ADD server-config /usr/local/tomcat

ADD webapps/ /usr/local/tomcat/webapps
