## Docker hub에  spring boot image 업로드

**Java 빌드 과정**

* windows의 패키지 관리자  **choco** 설치

* Gradle 설치
  * ```
    brew install gradle
    ```

* Jar 파일 실행

  * ```bash
    gradle bootJar #build/libs/ .jar 파일을 생성해줌
    java -jar build/libs/{jar 파일 이름}.jar
    ```

**Docker 이미지 로컬에서 업로드**

* Dockerfile 생성

  * ```dockerfile
    FROM adoptopenjdk/openjdk11:alpine-jre
    ARG JAR_FILE=target/*.jar
    COPY ${JAR_FILE} app.jar
    
    EXPOSE 9002
    ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]
    ```

* Docker Image 생성

  * ```bash
    # Back-End
    # Account-service
    docker build --build-arg JAR_FILE=build/libs/*.jar -t {DockerHub ID}/account-service:v1 .
    # Group-service
    docker build --build-arg JAR_FILE=build/libs/*.jar -t {DockerHub ID}/group-service:v1 .
    # Todo-service
    docker build --build-arg JAR_FILE=build/libs/*.jar -t {DockerHub ID}/todo-service:v1 .
    # Auth-service
    docker build --build-arg JAR_FILE=build/libs/*.jar -t {DockerHub ID}/auth-service:v1 .
    # Point-service
    docker build --build-arg JAR_FILE=build/libs/*.jar -t {DockerHub ID}/point-service:v1 .
    ```

* Docker Push

  * ```
    {DockerHub ID}#docker hub 로그인 확인
    Docker login
    
    docker push {DockerHub ID}/planit-front:v1
    docker push {DockerHub ID}/account-service:v1
    docker push {DockerHub ID}/group-service:v1
    docker push {DockerHub ID}/todo-service:v1
    docker push {DockerHub ID}/auth-service:v1
    docker push {DockerHub ID}/point-service:v1
    ```

**Docker 이미지 EC2에서 다운로드**

* ```
  docker pull {DockerHub ID}/planit-front:v1
  docker pull {DockerHub ID}/account-service:v1
  docker pull {DockerHub ID}/group-service:v1
  docker pull {DockerHub ID}/todo-service:v1
  docker pull {DockerHub ID}/auth-service:v1
  docker pull {DockerHub ID}/point-service:v1
  ```