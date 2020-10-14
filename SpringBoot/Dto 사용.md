### Dto (Data Transfer Object)

> 도메인 레이어에 있는 도메인 오브젝트의 속성을 노출시키지 않으면서, 적절하게 필요한 부분만 사용자 인터페이스에 보여줄 수 있다.



* Account.java

```java
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@Column(name = "account_id", nullable = false, unique = true)
	private String accountId;
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	private String birth;
	private String gender;
	private String address;
	private String phone;
	private String introduce;
	private int rating;
	private String usedAt;
	private String displayAt;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//	@JsonManagedReference
    private List<Todo> todos= new ArrayList<Todo>();
```

* AccountSignupDto

* ```java
  @AllArgsConstructor
  @NoArgsConstructor //파라미터가 없는 생성자를 생성
  @Getter
  @Setter
  public class AccountSignupDto implements Serializable{
  	
  	private String accountId;
  	private String name;  
  	private String email; 
  	private String password;
  	private String birth;
  	
      //생성자
  	public AccountSignupDto(Account account) {
  		BeanUtils.copyProperties(account, this);
  	}
      
  	//toDomain
  	public Account toDomain(){
  		Account account = new Account();
  		//class간의 property 복사
  		//this필드의 할당된 값을 account필드에 넣어준다.
  		//ex) account.setName(AccountSignupDto.getName())과 같다.
  		BeanUtils.copyProperties(this,account); //this:원본객체, account:복제 대상 객체
  		return account;	
  	}
  }
  ```

* 1. accountSignupDto를 받아 account객체로 변환한다.

  ![Dto에서 toDmain 사용이유](C:\Users\SAMSUNG\LMH\TIL\SpringBoot\image\Dto에서 toDmain 사용이유.png)

  * AccountController

  * ```java
    @ApiOperation(value ="회원가입 기능", notes = "AccountSignupDto타입을 이용하여 데이터를 받아온다.")
    	@PostMapping("signup/")
    	public Account addAccount(@RequestBody AccountSignupDto accountSignupDto) {
    		Account newAccount = accountSignupDto.toDomain();
    		return accountService.addAccount(newAccount);
    	}
    ```

* 2. 생성자가 필요한 이유

![](C:\Users\SAMSUNG\LMH\TIL\SpringBoot\image\Dto에서 생성자가 필요한 이유.png)