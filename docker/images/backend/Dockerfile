# Runtime image uses the latest WildFly app server (includes a recent JDK).
FROM quay.io/wildfly/wildfly:latest

# Place your Spring-based WAR in the build context (e.g., build/app.war).
# The WAR should include Flyway migrations on the classpath.
ARG WAR_FILE=app.war

COPY ${WAR_FILE} /opt/jboss/wildfly/standalone/deployments/ROOT.war

EXPOSE 8080
