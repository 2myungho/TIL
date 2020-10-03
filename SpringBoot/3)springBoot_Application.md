## 회원 도메인 개발

* **MemberRepository**

```java
@Repository
@RequiredArgsConstructor
public class MemberRepository {

//	@PersistenceContext
//	@Autowired //스프링 부트가 @Autowired로 사용할 수 있게 지원을 해준다.
	// @RequiredArgsConstructor을 통해 자동으로 생성자를 생성하여 final 선언이 가능하다.
	//Spring boot에서는 @Autowired 생략이 가능하다.
	private final EntityManager em; //스프링이 엔티티 매니저를 만들어줌
	
	//저장 로직 //jpa가 제공하는 save
	public void save(Member member) {
		em.persist(member);
	}// persist 하면 영속성 컨텍스트에 Member엔티티를 넣는다. 트랜젝션이 커밋되는 시점에 DB에 저장된다.
	
	//조회 로직 //jpa가 제공하는 find
	public Member findOne(Long id) {
		return em.find(Member.class, id); //Member member = em.find(Member.class, id);
	}//find(type, pk)
	
	//회원 목록을 뿌리려면 List가 필요하다
	public List<Member> findAll(){
		return em.createQuery("select m from member m", Member.class) //JPQL사용
				.getResultList();
	}
	
	//파라미터 바인딩, 이름에 의해서 조회
	public List<Member> findByName(String name){
		return em.createQuery("select m from Member m where m.name = :name",Member.class) //sql문의 바인딩을 생각해보자.
				.setParameter("name", name)
				.getResultList();
	}
}
```

* **MemberService**

```java
@Service
@Transactional(readOnly = true) //(readOnly = true)사용시 조회하는 곳에서는 성능이 올라간다. //읽기 전용
@RequiredArgsConstructor //lombok 어노테이션. final이 있는 필드에 생성자 inject를 자동으로 주입해준다.
public class MemberService {

//	@Autowired
	//Spring boot에서는 @Autowired 생략이 가능하다.
	//필드 주입은 final을 선언할 수 없지만 생성자 주입은 final을 선언함으로써 객체가 변하지 않도록 불변성
	private final MemberRepository memberRepository;
	
	//회원 가입
	@Transactional(readOnly = false) //jpa는 데이터를 변경하려면 기본적으로 Transactional 안에 있어야 한다. //쓰기 전용은 따로 설정해준다.
	public Long join(Member member) {
		validateDuplicateMember(member); //중복 회원 검증
		memberRepository.save(member);
		return member.getId(); //영속성 컨텍스트에 값이 저장되어 있어서 값 추출 가능
	}
	
	//"select m from Member m where m.name = :name" 쿼리가 실행되어서 !findMembers.isEmpty() findMembers에 member가 이미 있다면 예외 발생  
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	//회원 전체 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	//한 건만 조회
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
```

* **MemberServiceTest**

* @Test(expected = IllegalStateException.class) : try / catch 문을 생략해서 더 간결하게 표현이 가능하다.

* 회원가입 예제에서는 커밋이 되지 않고 롤백이 되지만 중복회원예외()메서드 실행시에는 insert쿼리가 나타난다.

* 1. JPA는 트랜잭션 커밋 시점에 플러시가 발생한다.

  2. JPA는 JPQL을 실행할 때 플러시가 발생한다.

```
@Test
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim2");
		
		//when
		memberService.join(member1);
		try {
			memberService.join(member2); //예외가 발생해야 한다.
		} catch (IllegalStateException e) {
			return;
		}
		
try / catch 문을 생략해서 더 간결하게 표현이 가능하다.

@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim2");
		
		//when
		memberService.join(member1);
		memberService.join(member2);

```

```java
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //테스트에서 사용할 시에는 기본 롤백(초기화)을 해버린다.
public class MemberServiceTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	@Autowired EntityManager em;

	@Test
//	@Rollback(false)
	public void 회원가입() throws Exception {
		//given 이런게 주어졌을 때
		Member member = new Member();
		member.setName("kim");
		
		//when 이렇게 하면
		Long savedId = memberService.join(member);
		
		//then 결과가 이렇게 나와야한다.
//		em.flush(); //영속성 컨텍스트 안에 있는 memeber의 변경이나 등록 내용을 반영한다. 내용을 확인하고 싶을 때 사용
		assertEquals(member, memberRepository.findOne(savedId));
	}
	
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");
		
		//when
		memberService.join(member1);
		memberService.join(member2); //예외가 발생해야 한다.

		//then
		fail("예외가 발생해야 한다."); //오류가 생기면 메세지를 보여준다.
	}

}
```

* 메모리DB 테스트 사용해보기
  * src/test 패키지에 resources 폴더 생성
  * resources 폴더에 application.yml 붙여넣기
  * http://www.h2database.com/html/cheatSheet.html 에 들어가서 in-Memory `jdbc:h2:mem:test` 복사
  * application.yml 의 URL에 붙여넣기
  * H2가 실행되지 않아도 동작을 한다.
  * STS에서 작동 안 되는 경우가 있는 거 같다 (본인 포함.)

## 상품 도메인 개발

* 상품 등록
* 상품 목록 조회
* 상품 수정



* 상품(item) 엔티티 개발 (비즈니스 로직 추가)
  * 데이터를 가지고 있는 곳에 비즈니스 로직을 가지고 있는 것이 좋다.

```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	@ManyToMany(mappedBy = "items") 
	private List<Category> categories = new ArrayList<Category>();
	
	///== 비즈니스 로직 ==/ //데이터를 가지고 있는 곳에 비즈니스 로직을 가지고 있는 것이 좋다.(응집력)
	
	//재고수량 증가 로직 (stock 증가)
	 public void addStock(int quantity) {
		 this.stockQuantity += quantity;
	 }
	 //재고수량 감소 로직 //0이하로 감소 되면 안 됨
	 public void removeStock(int quantity) {
		 int restStock = this.stockQuantity - quantity;
		 if(restStock < 0) {
			 throw new NotEnoughStockException("need more stock");
		 }
		 this.stockQuantity = restStock;
	 }
}
```

* NotEnoughStockException 공통으로 사용할 exception
  * 영상에서는 인텔리j를 사용하여 override 했는데 STS(이클립스)에는 이 코드대로 나오지 않았다.

```JAVA
public class NotEnoughStockException extends RuntimeException {
	public NotEnoughStockException() {
	}

	public NotEnoughStockException(String message) {
		super(message);
	}

	public NotEnoughStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughStockException(Throwable cause) {
		super(cause);
	}
}
```

* ItemRepository

```java
@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;
	
	//신규 등록 & 업데이트
	public void save(Item item) {
		if(item.getId() == null) { //이미 등록 된 상품인지 아닌지 검사
			em.persist(item); //신규등록
		}else {
			em.merge(item); //업데이트
		}
	}
    
	//특정 조회
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
    
	//전체 조회
	public List<Item> findAll(){
		return em.createQuery("select i from Item i", Item.class)
				.getResultList();
	}
}

```





### 공부 중 생겼던 에러

```java
Annotation-specified bean name 'memberRepository' for bean class [jpabook.jpashop.repository.MemberRepository] conflicts with existing, non-compatible bean definition of same name and class [jpabook.jpashop.MemberRepository]

/*test에 MemberRepository memberRepository로 일름이 같은 bean이 충돌 되어 생긴 에러이다. 폴더 이동시에도 생긴다고 한다.
1. 해결 방법은 Gradle tasks에 build폴더로 들어간다.
2. clean을 클릭한다.
3. build를 클릭한다.*/
    
    
NULL not allowed for column "ID"; SQL statement:

//나는 ID의 column name을 member_id로 주었는데 h2에 접속해서 확인해보면 ID라는 컬럼이 존재한다.
//h2의 오류라고 하는 것 같다. 이럴 때는 ALTER TABLE MEMBER DROP COLUMN ID;을 실행해서 컬럼을 삭제해준 뒤 다시 실행을 해주자
```

