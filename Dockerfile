# Use Tomcat 10 with Java 17
FROM tomcat:10.1-jdk17
LABEL maintainer="Gnana Murali"

# Clean default Tomcat apps (optional but recommended)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the WAR file (now named ROOT.war)
COPY ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
