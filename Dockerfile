FROM tomcat:10.1-jdk17
LABEL maintainer="Gnana Murali"

# Copy WAR file to Tomcat webapps directory
COPY EduTestPortal.war /usr/local/tomcat/webapps/

# Expose default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
