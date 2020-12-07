## Docker 에서 maria DB 연결

Docker에서 mariaDB 이미지 다운 받기

* ```
  docker pull mariadb
  ```

MariaDB 도커 컨테이너 실행

* ```
  docker run --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=mariadb mariadb
  
  --name : 컨테이너 이름
  
  -d : 데몬 실행
  
  -p : 로컬 port와 컨테이너 포트 연결
  
  -e : 환경 변수 설정
  ```

MariaDB 접속

* ```
  docker exec -it {컨테이너 아이디} mysql -u root -p
  ```



## mariaDB 설정

사용자 생성

* ```
  create user '사용자 이름'@'%' identified by '비밀번호';
  ```

  





## Docker compose 사용

Docker compose는 여러 개의 컨테이어로 구성된 애플리케이션을 관리하기 위한 간단한 오케스트레이션 도구입니다.

Docker compose를 사용하면 컨테이너 실행에 필요한 옵션을 docker-compose.yml 파일에 적어둘 수 있습니다.

Docker compose 파일은 여러개의 도커 이미지를 이용해 여러 개의 컨테이너를 만드는 파일이다.



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

* 