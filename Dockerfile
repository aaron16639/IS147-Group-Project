FROM openjdk:24
WORKDIR /app
COPY src/ /app/src/
RUN javac src/*.java src/*/*.java
CMD ["java", "-cp", "/app/src", "ERPSystemMain"]
