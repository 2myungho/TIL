#### HEAD

> HEAD는 **현재 체크아웃된 커밋**을 가리킵니다. 즉 **현재 작업중인 커밋**입니다.
>
> 커밋을 분리한다는 것은 HEAD를 브랜치 대신 커밋에 붙이는 것을 의미합니다.
>
> ```bash
> git checkout [커밋 해쉬]
> # 커밋을 엄청나게 길게 작성했을 경우
> git checkout HEAD^ #바로 직전의 커밋 (부모 커밋)
> git checkout HEAD^^ #전전의 커밋
> git checkout HEAD~4 #4번 뒤에 커밋으로 돌아감
> ```
>
> HEAD를 옮김으로써 커밋 전의 기록을 다시 확인? 할 수 있다.



#### 브랜치 강제로 옮기기

> **-f 옵션**을 이용해서 **브랜치를 특정 커밋에** 직접적으로 **재지정**할 수 있습니다.
>
> 브랜치를 옮길 때는 **브랜치에 HEAD가 있어야 합니다.**
>
> 브랜치가 옮겨질 때 **HEAD는 자리에 남아** 있고 **브런치만 commit 위치로 이동**합니다.
>
> ```bash
> git branch -f main HEAD~3 #3번 뒤에 커밋으로 브런치 이동
> git branch -f main [commit이름] #특정 커밋으로 브런치 이동
> ```



#### 작업 되돌리기

**git reset**

> 애초에 커밋하지 않은 것처럼 예전 커밋으로 옮기는 것.
>
> 
>
> 다른 사람이 작업하는 리모트 브랜치에는 쓸 수 없다. 즉 로컬 브랜치에만 사용한다.
>
> ```bash
> git reset HEAD~1
> ```



**git revert**

> 변경분을 되돌리고, 이 되돌린 내용을 다른 사람들과 공유하기 위해서는, git revert를 사용해야합니다.
>
> git revert를 사용하고 다음 commit을 하면 어떤 commit이 Revert 되었는지 확인할 수 있습니다.

<img src="image/revert.png" >