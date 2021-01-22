## ec2 ubuntu 서버에서 docker 설치하기

* ```bash
  //업그레이드가 필요한 패키지를 최신 버전의 패키지로 업데이트
  sudo apt update
  
  //도커를 위해 필요한 패키지 설치
  
  //패키지 관리자가 https를 통해 데이터 및 패키지에 접근할 수 있도록 한다.
  sudo apt install apt-transport-https 
  
  //certificate authority에서 발행되는 디지털 서명. SSL 인증서의 PEM 파일이 포함되어 있어 SSL 기반 앱이 SSL 연결이 되어있는지 확인할 수 있다.
  sudo apt install ca-certificates
  
  //특정 웹사이트에서 데이터를 다운로드 받을 때 사용
  sudo apt install curl
  
  //*PPA를 추가하거나 제거할 때 사용한다.
  //PPA (Personal Package Archive) : 개인 패키지 저장소
  sudo apt install software-properties-common
  
  //Docker 설치
  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
  sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
  sudo apt update
  apt-cache policy docker-ce
  sudo apt install docker-ce
  ```


##  로컬에서 react를 docker hub에 업로드, 이미지 생성

* 리액트 프로젝트를 만든다

  * ```bash
    //build 폴더가 생성된다.
    npm run build
    ```

* Docker 컨테이너를 생성하기 위해서는 Docker이미지가 있어야 한다. 그리고 이미지를 생성하는 방법은 **DockerFile**을 이용해서 도커라이징을 해야한다.

  * ```dockerfile
    # nginx 이미지를 사용합니다. 뒤에 tag가 없으면 latest 를 사용합니다.
    FROM nginx
    
    # root 에 app 폴더를 생성
    RUN mkdir /app
    
    # work dir 고정
    WORKDIR /app
    
    # work dir 에 build 폴더 생성 /app/build
    RUN mkdir ./build
    
    # host pc의 현재경로의 build 폴더를 workdir 의 build 폴더로 복사
    ADD ./build ./build
    
    # nginx 의 default.conf 를 삭제
    RUN rm /etc/nginx/conf.d/default.conf
    
    # host pc 의 nginx.conf 를 아래 경로에 복사
    COPY ./nginx.conf /etc/nginx/conf.d
    
    # 80 포트 오픈
    EXPOSE 80
    
    # container 실행 시 자동으로 실행할 command. nginx 시작함
    CMD ["nginx", "-g", "daemon off;"]
    ```

* nginx.conf 를 생성

  * ```javascript
    server {
        listen 80;
        location / {
            root    /app/build;
            add_header 'Access-Control-Allow-Origin' *;
            index   index.html;
            try_files $uri $uri/ /index.html;
        }
        location /api/auth{
            proxy_pass http://ec2-3-35-119-242.ap-northeast-2.compute.amazonaws.com:9000/api/auth;
        }
        location /api/accounts {
            proxy_pass http://ec2-3-35-119-242.ap-northeast-2.compute.amazonaws.com:9010/api/accounts;
        }
        location /api/todos {
            proxy_pass http://ec2-3-35-119-242.ap-northeast-2.compute.amazonaws.com:9002/api/todos;
        }
        location /api/groups {
            proxy_pass http://ec2-3-35-119-242.ap-northeast-2.compute.amazonaws.com:9003/api/groups;
        }
        location /api/ponits {
            proxy_pass http://ec2-3-35-119-242.ap-northeast-2.compute.amazonaws.com:9004/api/points;
        }
    }
    // location은 Proxy 서버로 들어오는 요청 URL에 따라 맵핑된다.
    // Proxy_pass는 들어온 요청을 어디로 포워딩 해두즌지 지정한다.
    // 포워딩이란 : 
            
    //80 포트에 / 경로로 들어오면 /app/build 폴더에서 index.html 을 찾음.
    ```

* Docker image 생성

  * ```bash
    docker build -t dlaudghtn/planit-react:v1 .
    ```

* 로컬에서 컨테이너 실행

  * ```bash
    docker run -d --name my-react-app -p 8300:80 dlaudghtn/planit-react:v1
    
    docker ps로 컨테이너가 실행중인지 확인
    localhost:8300으로 접속
    ```

* 도커 허브에 업로드

  * ```bash
    docker push dlaudghtn/planit-react:v1
    ```

* EC2 서버에서 Docker image 다운 받기

  * ```bash
    docker pull dlaudghtn/planit-react:v1
    ```

* EC2 서버에서 컨테이너 실행하기

  * ```
    docker run -d -p {hostPort(80)}:{container port(80)} {docker image}:{tag}
    ```



* 로컬에서 이미지를 도커허브에 업로드 하고, EC2 서버에서 이미지 다운로드 해서 컨테이너를 실행시킨다.









* Docker Compose를 사용한다.
  * 복수 개의 컨테이너를 실행시키는 도커 애플리케이션이 정의를 하기 위한 **툴**입니다.
  * Compose를 사용하면 YAML 파일을 사용하여 애플리케이션의 서비스를 구성할 수 있습니다.
  * 여러 컨테이너를 생성하는 Docker 어플리케이션을 정의하고 실행하도록 하는 도구.
  * 각 어플리케이션의 설정과 연관된 모든 서비스를 함께 설정 및 실행할 수 있다. 









* 참고 사이트
  * https://hello-bryan.tistory.com/169
  * https://velog.io/@wimes/AWS-EC2%EC%97%90-Docker-%EC%84%A4%EC%B9%98-%EB%B0%8F-Dockerfile%EB%A1%9C-%EC%9B%B9%EC%84%9C%EB%B2%84-%EA%B5%AC%EB%8F%99%EC%8B%9C%ED%82%A4%EA%B8%B0