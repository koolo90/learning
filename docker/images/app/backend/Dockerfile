# Runtime image uses the latest WildFly app server (includes a recent JDK).
FROM jboss/wildfly:latest

ENV JBOSS_BIND_ADDRESS_MANAGEMENT=0.0.0.0
# Place your Spring-based WAR in the build context (e.g., build/app.war).
# The WAR should include Flyway migrations on the classpath.
ARG WAR_FILE=app.war

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

# COPY ${WAR_FILE} /opt/jboss/wildfly/standalone/deployments/ROOT.war

EXPOSE 8080 9990
