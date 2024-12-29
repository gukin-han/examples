# 1. 베이스 이미지 설정
FROM eclipse-temurin:23-jdk-alpine

# 2. 컨테이너 내 작업 디렉토리 설정
WORKDIR /app

# 3. JAR 파일 복사
COPY build/libs/docker-example-1-0.0.0.jar app.jar

# 4. JAR 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]