git merge에 대해서 알아보겠다.

git merge 명령어는 이름 그대로 두 개의 브랜치를 병합하는 명령어이다.

git merge의 사용 방법에는 두 가지가 있다.

1. fast-forward 방식

   * fast forward는 master가 추가적인 커밋이 없을 때 사용된다.

   * ```
     명령어는
     git merge feature
     master에서 합치고 싶은 브랜치의 브랜치를 입력한다.
     ```

   * 또 다른 이슈가 발생해서 feature 브랜치를 생성하고 추가 작업을 진행한다.

   * 여기서 master 브랜치는 새로운 커밋을 발생시키지 않고, feature가 있는 최신 상태의 커밋으로 이동하게 된다.

   * 이것이 fast-forward 방식이다.

2. 3-way-merge

   * 3-way merge는 master에 추가적인 커밋이 있을 때 사용된다.

   * feature와 master가 각자의 기능이 추가 되면서 병합을 하려고 할 때 master 브랜치로 이동한다.

   * ```
     명령어는
     git merge feature
     master 브랜치에서 feature 브랜치를 병합한다.
     (뒤에는 합치고 싶은 브랜치를 입력)
     ```



rebase 그림 그리면서 fast forward 그림과 같이 master에 추가적인 커밋이 없으므로 fastforward 방식으로 merge를 사용할 수 있다.



위의 merge와 rebase 명령어를 비교했을 때의 차이점으로는

merge는 브랜치의 최종 커밋을 가지고 merge 커밋을 만들어 병합을 하고,

rebase는 지정한 브랜치를 베이스로 기존의 커밋을 복사해 새로운 커밋으로 병합을 한다.







merge는 브랜치를 통합하는 것이고 리베이스는 브랜치의 베이스를 옮긴다.