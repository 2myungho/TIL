Java 15 사용

IDE : IntelliJ 



스프링 부트 스타터 사이트에서 스프링 프로젝트 생성



#### project

##### Maven Project

> 내가 사용할 라이브러리 뿐만 아니라 해당 **라이브러리가 작동하는데 필요한 다른 라이브러리들까지 관리**하여 네트워크를 통해 **자동으로 다운** 받아준다.
>
> Meven Lifecycle은 **프로젝트를 빌드**하는데 관련된 **순차적인 단계**들이다.

> Maven의 Lifecycle은 크게 **default(기본), Clean, site** 세가지 라이프사이클로 나누고 있다.

<img src="https://user-images.githubusercontent.com/52882578/108439090-f6b8d780-7293-11eb-9642-2ecc6f42bfe1.jpg" alt="Maven_Lifecycle" style="zoom:80%;" />

<span style="font-size:14px">출처 : https://www.slideshare.net/ssuser5445b7/ss-56566336?qid=927855f5-7c8a-4f88-a834-d31292324fd2&v=&b=&from_search=4 </span>

> **phase :**
>
> * phase는 빌드 라이프사이클에서 빌드 단계와 각 단계의 순서만을 정의하고 있는 개념이다.
> * 빌드 과정에서 phase가 빌드 작업을 하지 않는다.
> * 빌드 작업은 phase에 연결되어 있는 플러그인의 **goal**이 한다
>
> **goal :**
>
> * 내가 실행할 명령이다.
> * 플러그인은 하나 이상의 goal의 집합체이다.



##### Gradle Project

> 그래들은 그루비 (Groovy)를 기반으로 한 **빌드 도구**이다.
>
> Ant와 Maven과 같은 빌드 도구의 단점을 보완하고 장점을 취합하여 만든 오픈 소스 빌드 도구이다.

> **gradle wrapper**
>
> * 이미 존재하는 프로젝트를 **새로운 환경에 설치할 때 별도의 설치나 과정 없이 곧 바로 빌드**할 수 있게 하기 위함
> * 사용자가 Gradle이 설치되어 있지 않아도 Gradle tasks를 실행할 수 있도록 해주는 작은 **script, jar 및 등록 정보 파일**
> * wrapper를 생성하면 사용자가 프로젝트를 만든 사람과 동일한 버전의 Gradle을 사용할 수 있음
>
> ##### task 설정
>
> * Gradle에는 자신의 프로젝트에서 구성할 수 있는 task 라이브러리가 함께 제공됨



#### start.spring.io

![start](https://user-images.githubusercontent.com/52882578/108439129-09cba780-7294-11eb-86b8-17d7798c49b4.PNG)

> **Spring Boot** :
>
> * SNAPSHOT은 아직 만들고 있는 버전 M1은 정식 릴리즈 된 버전이 아니다.
>
> **Project Metadata :**
>
> * Group : 보통 기업 도메인명을 작성한다.
> * Artifact : 빌드되어 나올 때 결과물 (프로젝트명)

![start2](https://user-images.githubusercontent.com/52882578/108439510-bf96f600-7294-11eb-9ae8-3bbbe4c8b164.PNG)

> 라이브러리 선택시 Spring Web 선택


#### 세팅

![start3](https://user-images.githubusercontent.com/52882578/108441199-fae6f400-7297-11eb-8b28-0bffcf328f13.PNG)

> Build and run using, Run tests using 이 Gradle로 되어 있으면 run 할 때 그래들을 통해 실행하기 때문에 느릴 수 있다.





#### Libray

![libray](https://user-images.githubusercontent.com/52882578/108441745-20c0c880-7299-11eb-9617-e383e6dc0d72.PNG)

> 서로 의존되는 라이브러리들을 자동으로 가져와준다.
>
> 시작할 때 spring-web과 thymeleaf 만 선택했지만 이 라이브러리를 실행하기 위한 라이브러리들을 자동으로 가져와준다. (하위 항목이 생김)



#### Gradle 빌드

> ```bash
> #해당 프로젝트 디렉터리로 이동 후
> ./gradlew build
> #build 폴더로 이동
> #libs 폴더로 이동
> #.jar 파일이 생성된 것을 확인할 수 있음
> 
> ./gradlew clean build #빌드 파일을 완전히 지우고 다시 빌드 
> ```