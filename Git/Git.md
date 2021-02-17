## Git

#### commit

> **git commit** 은 **vim모드**에서 **버전의 메세지를 작성**한다.
>
> 이렇게 vim mode에서 메세지 작성 작업을 **명령창**에서 바로할 수 있는 commit의 옵션이 **-m** 이다.
>
> vim에 들어가 메세지를 작성하는 작업을 skip하고 명령창에서 메세지를 작성할 수 있다.
>
> ```bash
> git commit -m "first commit"
> ```



#### branch

> 브랜치는 **코드를 통째로 복사**하고 나서 원래 코드와는 상관 없이 독립적으로 개발할 수 있게 해줍니다.
>
> 만들어진 각각의 브랜치는 **다른 브랜치의 영향을 받지 않기 때문에** 여러 사람이 동시에 작업을 할 수 있습니다. 
>
> **브랜치는** 놀랍도록 **가볍기 때문에** 많이 만들어도 **메모리나 디스크에 부담이 되지 않습니다.**
>
> **브랜치로 이동하기**
>
> 1. 브랜치 생성 후 이동
>
>    * ```bash
>      git branch [브랜치명]
>      git checkout [브랜치명]
>      ```
>
> 2. 브랜치 생성과 동시에 이동
>
>    * ```bash
>      git checkout -b [브랜치명]
>      ```



#### merge

* master 브랜치 commit

<img src="image/master_branch.png" style="zoom:80%;"  >

* branch_1 생성 후 내용 추가 + commit  

<img src="image/branch_1.png" style="zoom:80%;"  >

* master 브랜치로 이동해서 merge

  > ```bash
  > git merge branch_1
  > ```
  >
  > merge가 성공적으로 완료하면 branch_1의 내용과 master 브랜치의 내용이 병합된다.
  >
  > master 브랜치에서 push 하면 **commit 로그에 branch_1이 성공적으로 기록**된다.



#### merge 충돌

* branch_1 브랜치 commit

<img src="image/merge_3.png" style="zoom:80%;"  >

* branch_2 브랜치 commit

<img src="image/merge_4.png" style="zoom:80%;"  >

* master 브랜치에서 branch_1 merge 성공 후 branch_2 를 merge하면 **충돌이 발생**한다.

<img src="image/merge_2.png" >

<img src="image/merge_1.png" style="zoom:80%;"  >

> master 브랜치에 충돌난 부분을 처리하고 add / commit / push 하면 정상적으로 처리된다.



#### rebase

> 브랜치끼리의 작업을 접목하는 두번째 방법
>
> 기본적으로 커밋들을 모아서 복사한 뒤,  다른 곳에  떨궈 놓는 것이다.
>
> rebase의 장점으로는 **커밋들의 흐름을 보기 좋게 한 줄로 만들 수 있다**는 장점이 있습니다.

> master 브랜치가 아닌 생성한 test 브랜치로 이동해서 master 브랜치를 base 삼아 Rebase한다는 말입니다.
>
> ```bash
> git checkout test
> git rebase master
> #또는
> git rebase master test #test(ex v2)가 master(ex v1)라인으로 복사 (ex v2')
> ```
>
> 그러면 내부에서 master브랜치가 base가 되고, 변경 사항의 차이를 임시 저장하는 공간인 Patch에 저장합니다.
>
> 그리고 master 브랜치에 patch들이 적용됩니다.
>
> 마지막으로 Fast-forward 시키는 과정까지 완료하면 커밋 히스토리가 깔끔하게 정렬됩니다.
>
> 참고 문서
>
> > https://velog.io/@kwonh/Git-Rebase%EB%9E%80#rebase%EC%9D%98-%EC%9C%84%ED%97%98%EC%84%B1



#### merge와 rebase

> merge와 rebase의 실행 결과는 같지만 커밋 히스토리가 달라진다.
>
> **merge**는 쉽고 안전하지만 커밋 히스토리가 지저분할 수 있다.
>
> **rebase**는 제대로 사용하지 못할 경우 위험할 수 있지만 **커밋 히스토리를 깔끔하게 관리**할 수 있다.

왼쪽이 rebase 오른쪽이 merge이다.

<img src="image/merge_rebase.png" >





질문

> 명령줄에서 master에 merge를 하는 것과 push를 통한 풀 리퀘스트를 사용해서 master에 pull 받는 방법 중 무엇이 좋은가