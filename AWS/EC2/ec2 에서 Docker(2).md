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



## Docker compose 사용

Docker compose는 여러 개의 컨테이어로 구성된 애플리케이션을 관리하기 위한 간단한 오케스트레이션 도구입니다.

Docker compose를 사용하면 컨테이너 실행에 필요한 옵션을 docker-compose.yml 파일에 적어둘 수 있습니다.

Docker compose 파일은 여러개의 도커 이미지를 이용해 여러 개의 컨테이너를 만드는 파일이다.



깃 설치

* ```
  sudo apt install git
  git --version
  
  git config --global user.name "이름"
  
  git config --global user.mail "메일"
  
  git config --global color.ui "auto"
  ```



docker compose 설치

* ```
  sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
  ```

권한 설정

* ```
  sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
  ```

심볼릭 링크 설정

* ```
  sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compos
  ```

버전 확인

* ```
  docker-compose -version 
  ```


Docker compose  실행

* ```
  docker-compose up -d
  ```

Container와 image 생성 됨, compose 파일에 DB설정을 해두면 DB도 생성 됨





보안그룹, react 프록시 수정