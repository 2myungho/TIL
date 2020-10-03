보통 다대다 관계를 사용하지 않기 때문에 다대다 관계를 일대다 다대일의 관계로 설계

다대일(N : 1)

* 사람은 가족이 있습니다.
* 사람은 **하나의 가족에만 소속**될 수 있습니다.
* 사람과 가족은 다대일 관계입니다.



일대다(1 : N)

* 사람과 가족의 관계는 다대일이지만, 가족과 사람의 관계는 일대다이다.
* 일대다 관계는 여러 객체와 연관관계를 맺을 수 있기 때문에 컬렉션을 사용해야한다.



일대일(1 : 1)

* 일대일 연관관계일 때는 PK가 있는 곳에 연관관계 주인으로 설정하면 된다.



외래 키가 있는 곳을 연관관계의 주인으로 정해라.

양방향성 관계일 때 기본키는 한쪽에 반드시 있어야 한다.

연관관계의 주인이 아닐 경우 

```java
@OneToMany(mappedBy = "member")  //mappedBy는 order 테이블에 있는 member 필드에 의해서 맵핑 되었다를 의미.
```







## 엔티티 클래스 개발

### 엔티티 개발의 주의점!

* 엔티티에는 가급적 **Setter를 사용하지 말자**
  Setter가 모두 열려있다. 변경 포인트가 너무 많아서, 유지보수가 어렵다. 나중에 리펙토링으로 Setter 제거
* 값 타입은 **변경 불가능하게 설계**해야 한다.
  @Setter를 제거하고, 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만들자. 
  JPA 스펙상 엔티티나 임베디드 타입(@Embeddable)은 자바 **기본생성자**를  public 또는 **protected로 설정**해야한다.

```java
//주소 값 타입
@Embeddable //jpa Entity 안의 Column을 하나의 객체로써 사용하고 싶을 때 사용
@Getter
public class Address {
	private String city;
	private String street;
	private String zipcode;
	
	protected Address() {}
	
	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
}
```

* **모든 연관관계는 지연로딩으로 설정**

  > 지연로딩 (LAZY) : 
  > 	로딩 되는 시점에 LAZY설정이 되어 있는 엔티티는 프록시 객체로 가져온다.
  > 	후에 실제 객체를 사용하는 시점에 초기화가 된다.
  > 	DB에 쿼리가 나간다. 
  > 	ex) getTeam( )로 팀을 조회하면 프록시 객체가 조회가 된다. getId( ).getXXX( )으로 팀의 필드에 접근 할 때, 쿼리가 나간다.
  >
  > 즉시로딩 (EAGER) : 
  > 	실제 조회할 때 한 번에 쿼리로 다 조회해서 가져온다.
  > 	실무에서 사용시 예상치 못한 SQL이 발생한다.
  > 	JPQL에서 N+1 문제를 일으킨다.
  >
  > ```JAVA
  > @ManyToOne(fetch = FetchType.EAGER) //Order class를 조회할 때 member을 join해서 같이 조회한다. //보통 한 건 조회할 때 사용.
  > 	@JoinColumn(name = "member_id") 
  > 	private Member member;
  > //JPQL select o From order o; -> SQL select * from order로 변경
  > ```
  >
  > 

  즉시로딩 ( EAGER )은 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어렵다.
  실무에서 **모든 연관관계는 지연로딩( LAZY )으로 설정해야 한다.**
  **OneToOne, ManyToOne은 default fetch가 즉시로딩 ( EAGER )이기 때문에 설정을 해주어야 한다.**

* 연관관계 편의 메서드 작성

  * **양방향 연관관계**에서 각각 호출하다 보면 실수로 둘 중 하나만 호출해서 양방향이 깨질 수가 있다
  * 연관관계 메서드는 컨트롤을 할 수 있는 클래스에서 작성한다.

  ```
  //==연관관계 편의 메서드 ==//
  	public void setMember(Member member) {
  		this.member = member;
  		member.getOrders().add(this);
  	}
  	public void addOrderItem(OrderItem orderItem) {
  		orderItems.add(orderItem);
  		orderItem.setOrder(this);
  	}
  	public void setDelivery(Delivery delivery) {
  		this.delivery = delivery;
  		delivery.setOrder(this);
  	}
  ```

  







## 공부하면서 발생한 오류

```java
[jpabook.jpashop.domain.Delivery.status] was annotated as enumerated, but its java type is not an enum [jpabook.jpashop.domain.DeliveryStatus]

//DeliveryStatus를 enum으로 만들어야 하는데 Java로 만들어서 발생한 오류입니다.
//public class DeliveryStatus -> public enum DeliveryStatus 로 변경해주면 됩니다.
    
매핑되지 않은 클래스를 대상으로하는 @OneToMany 또는 @ManyToMany
이와 관련된 오류가 나왔을 시에는 import가 제대로 되었는지 확인을 해야한다.
```

