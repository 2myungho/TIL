#  새로 알게된 내용







## Greedy

**9/4**

**우유축제** #14720

* ```java
  //버퍼 : 데이터를 한 곳에서 다른 곳으로 전송하는 동안 일시적으로 그 데이터를 보관하는 임시 메모리 영역
  //Stream : 바이트 단위의 입출력
  //Reader: 캐릭터 단위의 입출력
  //InputStreamReader : 바이트를 읽어서 지정된 문자 인코딩에 따라 문자로 변환하는데 사용한다.
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  StringTokenizer st = new StringTokenizer(br.readLine());
  //readLine() : 자바 파일 내용을 한 줄 씩 읽어들인다.
  //StringTokenizer : 입력 내용을 가로로 나열 할 수 있다.
  ```



## 자료구조

**9/5**

**카드1** #2161

* ```java
  Queue<Integer> qu = new LinkedList<>(); //queue 인터페이스를 생성할 때는 LinkedList를 사용한다.
  poll() //객체 하나를 가져온다. 그 후 객체를 큐에서 제거한다.
  offer() //주어진 객체를 넣는다.
  isEmpty // List 안에 아무것도 없는 상태
  !queue.isEmpty() //queue가 빌 때까지
  ```

**9/6**

**단어순서 뒤집기** #12605

* ```java
  Stack<String> save = new Stack<>();
  push() //주어진 객체를 스텍에 넣는다.
  pop() //스텍의 맨 위 객체를 가져온다. 그 후 객체를 스텍에서 제거 한다.
  ```

**9/7**

**요세푸스 문제** #1158

* ```java
  queue.offer(queue.poll()); // 맨 앞에 있는 데이터를 맨 뒤로 돌릴 수 있다.
  queue.get(i); //인덱스 i에 대한 값을 출력한다.
  ```

**9/8**

**회전하는 큐** #1021

* ```java
  //Depue는 stack과 Queue 둘의 메소드를 사용할 수 있다.
  Deque // 큐의 양쪽 끌에서 삽입과 삭제가 모두 발생할 수 있는 큐
  Deque<Integer> qu = new LinkedList<Integer>(); //선언
  Deque.offer(Deue.poll()); // 맨 앞에 있는 데이터를 맨 뒤로 돌릴 수 있다.
  Deque.addFirst(Deue.pollLast()); // 맨 뒤에 있는 데이터를 맨 앞으로 돌릴 수 있다.
  
  //Queue를 사용했을 때 원하는 값의 인덱스를 가져오려면
  if (o != qu.peek()) {
  	for (Integer s : qu) {
  		cnt ++;
  		if(s == o) {
  			//ex) o의 입력 값이 5일 경우 cnt = 5
  		}			
  	}
  ```

**9/9**

**스택** #10828

* ```java
  //subString을 사용하여 입력 값에 대해 유동적인 조건을 줄 수 있었다.
  str = a.substring(5);
  	if(a.equals("push "+str)) {
  		sc.push(Integer.parseInt(str));
  	}
  ```

**9/10**

**집합의 표현**#1717 (혼자 풀기 실패)

* 백준 골드 난이도에 처음 도전했다가 실패를 경험했다.
* 메소드를 만들어서 풀이하는 방법을 경험해 보았다.
* 문제 내용을 이해하는데에도 상당한 시간이 소비되었다.

* ```java
  //배열 데이터 채우기
  public static void makeSet(int n){
          parent = new int[n + 1];
          for(int i = 0; i <= n; i++){
              parent[i] = i;
          }
      }
  //합집합 구현
  public static void merge(int a, int b) {
  	a = find(a);
  	b = find(b);
  	if (a == b) {
  		return;
  	}
  	parent[a] = b;
  }
  // 합병	
  public static int find(int a) {
  	if (a == parent[a]) {
  		return a;
  	}
  	return parent[a] = find(parent[a]);
  }
  ```