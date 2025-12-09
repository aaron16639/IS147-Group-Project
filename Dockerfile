# Use Amazon Corretto 21 (supports modern Java features like arrow syntax)
FROM amazoncorretto:21

# Set working directory
WORKDIR /app

# Copy source files from src directory
COPY src/ /app/

# Compile all Java files recursively (handles subdirectories and packages)
RUN javac *.java

# Set the entry point to run the main class
CMD ["java", "ERPSystemMain"]

# Optional: If you need to expose a port (uncomment if needed)
# EXPOSE 8080