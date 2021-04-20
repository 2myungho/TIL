git Repository (원격 저장소)



remote tracking branch (origin/master) 리모트 브랜치

원격 브랜치는 원격 저장소의 상태를 반영



#### 리모트 브랜치

> 내가 플랜잇 프로젝트를 진행할 때 로컬에서 브랜치를 생성해서 작업을 했었다. 그 때 작업한 브랜치에서 origin으로 push를 하게되면 원격 저장소(repository)에 리모트 브랜치가 생성된다.
>
> 로컬 브랜치는 나만 사용하지만 리모트브랜치는 
>
> ```bash
> git checkout origin/[리모트브랜치]
> ```
>
> 명령어로 다른 사람이 생성한 브랜치를 사용할 수 있다.
>
> 원격 브랜치를 통해 다른 사람이 작성한 코드를 손쉽게 가져올 수 있다.
>
> **push**
>
> > 로컬 브랜치를 push 하면 리모트 브랜치는 로컬 브랜치와 같은 위치로 이동하게 된다.

> 브랜치로 pull request가 종료되어서 merge까지 완료 되었다면 로컬 브랜치와 원격 브랜치를 삭제해 주어야 한다.



#### git fetch

> 원격 저장소에는 있지만 로컬에는 없는 커밋들을 다운로드 받는다.
>
> 우리의 원격 브랜치가 가리키는 곳을 업데이트 한다.

> git fecth는 **로컬에서 나타내는 원격 저장소의 상태**를 **실제 원격 저장소의 현재 상태**와 **동기화** 한다.

> 예를들어 A와 B가 레포지토리를 clone 받아 master브랜치로 작업하고 있다고 한다.
>
> A가 먼저 commit / push를 진행했다.
>
> b도 commit / push를 하려고 했지만 실행되지 않았다. 이유는 **원격 저장소의 커밋 히스토리가 달라졌기 때문**이다.
>
> git fetch를 실행하면 로컬에 원격 저장소의 상태를 반영한다.
>
> 그리고 git fetch를 실행한다고 해서 **로컬 작업이 변경되지 않는다.**



#### git pull

> git pull 을 실행하면 fetch와 merge가 동시에 가능하다.
>
> git pull을 merge가 아닌 rebase로 사용하고 싶다면 
>
> ```bash
> git pull --rebase;
> ```



#### main 브랜치에서 실수로 커밋을 했을 때

> 프로젝트를 진행할 때 보통 main 브랜치에서 작업하지 않습니다.
>
> 만약 main 브랜치에서 실수로 commit을 했을 때 되돌리기 위한 방법이 있습니다.
>
> 1. main 브랜치와 같은 위치에 test브랜치를 하나 생성합니다.
> 2. main 브랜치의 위치를 직전의 commit 으로 이동합니다.
> 3. test 브랜치를 git puth origin test 하면 test로 push가 됩니다.



#### 원격추적

> ```bash
> git checkout -b test o/main
> #또는
> git branch -u o/main test # test 브랜치가 o/main 을 추적하도록 설정합니다.
> git push origin test
> ```
>
> 위와 같이 실행하면 test 브랜치로 push 하면 원격 저장소의 main 브랜치로 push 된다.



#### git push origin main

> 해석: 
>
> 내 저장소에 있는 "main"이라는 이름의 브랜치로 가서 모든 커밋들을 수집합니다. 그다음 "origin"의 "main" 브랜치로 가서 이 브랜치에 부족한 커밋들을 채워 넣고 완료 되면 알려줍니다.
>
> ##### git push
>
> > HEAD가 원격저장소에 push하려는 브랜치에  체크아웃 되어 있지 않으면 명령에 실패합니다.
> >
> > git push origin main 까지 작성하면 HEAD가 다른 커밋에 있어도 main 브랜치를 push할 수 있습니다.



#### git push 인자

> ```bash
> git push origin main:newBranch #main 브랜치 push 후 branch 생성
> git push origin test^:main #test를 push 하면 origin/main이 직전 커밋에 위치합니다.
> ```



#### 원격 저장소 브랜치 삭제 & fetch 브랜치 추가

> ```bash
> git push origin :test #test 브랜치 제거
> git fetch origin :test #test 브랜치 생성
> ```

#### git pull origin

> ```bash
> git pull origin main:test # 원격 브랜치 main에 foo 브랜치 생성
> ```
>





#### 깃 명령어

````bash
 vim ~/.gitconfig

[alias]
    co = checkout
    rb = rebase -i
    st = status
    cm = commit
    pl = pull
    ps = push
    lg = log --graph --abbrev-commit --decorate --format=format:'%C(cyan)%h%C(reset) - %C(green)(%ar)%C(reset) %C(white)%s%C(reset) %C(dim white)- %an%C(reset)%C(yellow)%d%C(reset)' --all
    ad = add
    tg = tag -n
    df = diff
    br = branch
````



