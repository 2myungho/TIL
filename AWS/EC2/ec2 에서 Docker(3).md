## Docker 에서 maria DB pull

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


