#### git rebase

리베이스는 이름 그대로 브랜치의 base를 다시 설정한다는 의미입니다.

그림으로 설명을 드리자면

![rebase1](https://user-images.githubusercontent.com/52882578/116968652-e12e4800-acef-11eb-9258-1f9757c8b04f.PNG)

그림에서는 c3 커밋을 베이스로 feature 브랜치와 master 브랜치가 나누어져 있습니다.

이렇게 나누어져 있는 브랜치를 master브랜치를 베이스로 rebase 명령어를 통해 병합할 수 있습니다.
명령어는 git checkout feature로 이동하고 git rebase master 베이스로 설정할 브랜치를 입력합니다.

![rebase2](https://user-images.githubusercontent.com/52882578/116968651-e095b180-acef-11eb-83c8-c1e115527038.PNG)

이렇게 리베이스를 사용하면 커밋 히스토리를 간결하게 유지할 수 있습니다. 

#### git rebase -i

그리고 이미 커밋한 히스토리를 수정하거나 삭제하는 등의 작업을 할 수 있는 interactive  옵션이 있습니다.

![rebase3](https://user-images.githubusercontent.com/52882578/116968665-e7bcbf80-acef-11eb-9bd6-7cfede1c4822.PNG)

명령어로는 git rebae -i 커밋 해쉬, HEAD, 브랜치 등을 입력해서 사용할 수 있습니다.
gti rebase -i HEAD~4 명령어를 사용합니다. 
입력한 커밋의 다음 커밋부터 현재의 커밋 까지 출력 됩니다.
![rebase4](https://user-images.githubusercontent.com/52882578/116968661-e7242900-acef-11eb-90af-54f99676ba5e.PNG)


p, pick : 해당 커밋을 수정하지 않고 그냥 사용하겠다 라는 명령어
- 텍스트 에디터로 들어오면 커밋에 pick 명령어가 디폴트 값입니다.
- 커밋의 순서를 바꿀 수 있다. 
- 커밋을 지우면 커밋히스토리에서 커밋이 삭제 된다.

r, reword : 커밋 메세지를 수정하기 위한 명령어
- git reword 후 저장후 에디터를 닫으면 커밋을 변경할 수 있는 다른 에디터 창이 출력된다.

e, edit : 저장후 종료하면 reword 처럼 커밋 메세지를 수정할 수 있으며, 변경할 커밋으로 checkout되어 작업을 수정하거나 커밋 사이에 커밋을 추가하는 작업을 할 수 있다. 
- 커밋 메세지를 수정하려면 git commit --amend
- git add와 commit  명령어로 커밋과 커밋 사이에 commit을 만든 후 git rebase --continue로 진행중인 리베이스 과정을 종료한다.

s, squash : 해당 커밋을 이전 커밋과 합치는 명령어이다. 
- 방금 만든 a2.5 커밋앞에 s명령어를 입력하고 저장후 종료하면 a2와 합친다는 에디터 창이 출력된다.
- git log에서 합쳐진 커밋 메세지를 확인할 수 있다.

f, fixup : "squash"처럼 작동하지만  이전 커밋 메세지만 남기는 차이가 있다. 

e, exec : 리베이스를 저장후 종료할 때 실행할 쉘 명령어를 작성할 수 있습니다.
- 예를 들어 git log등을 남길 수 있다.

b, break : 해당 라인에서 리베이스를 일시중지 하는 명령어
- a2와 a3 사이에서 break 명령어를 사용하면  해당 커밋에서 a2에서 일시중지 됩니다. 일시중지된 커밋에서 추가적인 작업이 가능한 것을 확인했습니다.
- continue 명령어를 사용하면 재개할 수 있다.

d, drop : 해당 커밋을 명시적으로 삭제하는 명령어이다

- 커밋 리스트에서 지우는 것과 같은 기능이지만 어떤 커밋이 삭제 됐는지 출력 된다.





