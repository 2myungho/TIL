#### cmd build 시 "cannot find symbol" 에러

<img src="image\cmd error1.png" />

> cmd에서 빌드할 때 이 에러가 발생한다면 **-sourcepath**명령어를 사용해서 (.class)파일 경로를 알려준다.

![cmd error5](C:\Users\82108\LMH\TIL\TIL\Java\image\cmd error5.PNG)

> ```shell
> javac -d out -sourcepath src/BRIQUE_2 src/BRIQUE_2/*.java 
> 
> # -d out: (.class)파일을 저장할 out 디렉터리 생성
> # src/BRIQUE_2 : 소스 파일의 위치를 지정
> # src/BRIQUE_2/*java : 경로안 모든 .java 파일 컴파일
> ```

<img src="image\cmd error2.png" />

(out 디랙터리가  생긴 이미지)

> 이렇게 하고나서 클래스 파일이 있는 out / BRIQUE_2 디렉토리에 가서 java Server 을 실행하면!

<img src="image\cmd error3.png" />

> 기본 클래스를 찾거나 로드할 수 없다는 에러가 또 나옵니다..
>
> 두 번째 에러를 해결하기 위해서는 
>
> **패키지의 (BRIQUE_2) perent (out) 디렉터리에서  실행하되,**
>
> **실행할 클래스 파일의 이름은 패키지명.클래스파일명으로 입력해야 합니다.**

<img src="image\cmd error4.png" />

> 해결!!!
>
> 출처 : https://velog.io/@solar/Java-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%ED%84%B0%EB%AF%B8%EB%84%90%EC%97%90%EC%84%9C-javac%EB%A1%9C-%EC%BB%B4%ED%8C%8C%EC%9D%BC-%ED%9B%84-%EC%8B%A4%ED%96%89-%EA%B3%BC%EC%A0%95%EC%97%90%EC%84%9C-%EB%B0%9C%EC%83%9D%ED%95%9C-%EC%98%A4%EB%A5%98-%ED%95%B4%EA%B2%B0

