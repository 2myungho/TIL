# SQL (MySQL, Maria)

### 

### JOIN

#### LEFT JOIN

> A, B 테이블 중에 
>
> A 값의 전체 데이터와, 
>
> A의 KEY 값과 B의 KEY 값이 같은 결과를 리턴

**A 테이블**

|      | ID   | NAME   | AGE  |
| ---- | ---- | ------ | ---- |
| 1    | 1    | 이명호 | 17   |
| 2    | 2    | 정승훈 | 18   |
| 3    | 3    | 이은송 | 19   |
| 4    | 4    | 박민재 | 20   |
| 5    | 5    | 곽병선 | 21   |
| 6    | 6    | 여민호 | 22   |

##### B 테이블

|      | ID   | school     | explanation      |
| ---- | ---- | ---------- | ---------------- |
| 1    | 1    | 서울대     | 국내 최고 대학교 |
| 2    | 2    | 연세대     | 역시 최고        |
| 3    | 3    | 고려대     | 역시 최고..2     |
| 4    | 3    | 카이스트   | 가고 싶다..      |
| 5    | 5    | 하버드     | 괴물들..         |
| 6    | 6    | 멀캠대학교 | ㅜㅜ             |

> A와 B의 **LEFT JOIN** 실행

```SQL
select
 a.id,
 a.name,
 a.age,
 b.school,
 b.explanation
from a
left join b on a.id = b.id
order by a.id asc
;
```

##### 결과 

|      | ID    | NAME   | AGE  | school     | explanation      |
| ---- | ----- | ------ | ---- | ---------- | ---------------- |
| 1    | 1     | 이명호 | 17   | 서울대     | 국내 최고 대학교 |
| 2    | 2     | 정승훈 | 18   | 연세대     | 역시 최고        |
| 3    | **3** | 이은송 | 19   | 고려대     | 역시 최고..2     |
| 4    | **3** | 이은송 | 19   | 카이스트   | 가고 싶다..      |
| 5    | **4** | 박민재 | 20   | [NULL]     | [MULL]           |
| 6    | 5     | 곽병선 | 21   | 하버드     | 괴물들..         |
| 7    | 6     | 여민호 | 22   | 멀캠대학교 | ㅜㅜ             |

#### LEFT JOIN (IS NULL)

````SQL
select
 a.id,
 a.name,
 a.age,
 b.school,
 b.explanation
from a
left join b on a.id = b.id 
where b.id is null /*null 값만 출력*/
order by a.id asc
;
````

|      | ID    | NAME   | AGE  | school | explanation |
| ---- | ----- | ------ | ---- | ------ | ----------- |
| 1    | **4** | 박민재 | 20   | [NULL] | [MULL]      |



#### INNER JOIN

> ID 값이 서로 중복되는 값만 출력된다.

|      | ID    | NAME   | AGE  | school     | explanation      |
| ---- | ----- | ------ | ---- | ---------- | ---------------- |
| 1    | 1     | 이명호 | 17   | 서울대     | 국내 최고 대학교 |
| 2    | 2     | 정승훈 | 18   | 연세대     | 역시 최고        |
| 3    | **3** | 이은송 | 19   | 고려대     | 역시 최고..2     |
| 4    | **3** | 이은송 | 19   | 카이스트   | 가고 싶다..      |
| 5    | 5     | 곽병선 | 21   | 하버드     | 괴물들..         |
| 6    | 6     | 여민호 | 22   | 멀캠대학교 | ㅜㅜ             |





### 서브 쿼리

> **하나의 쿼리문 안에 포함되어 있는 또하나의 쿼리문**

##### Select 에서의 서브쿼리

> Select 절에서 서브쿼리를 사용할 경우 서브쿼리 값은 **하나만 출력**되어야 합니다.

##### From 에서의 서브쿼리

> 서브쿼리에서 조회된 값을 테이블처럼 사용할 수 있습니다.
>
> 별칭도 사용 가능합니다.
>
> Join으로 사용 가능합니다.

```sql
INNER JOIN (
 	SELECT c.emp_no, d.dept_name
 	from current_dept_emp c
 	INNER JOIN departments d
 	ON c.dept_no = d.dept_no
 	order by c.emp_no ASC
	) dept
ON e.emp_no = dept.emp_no
```

##### Where에서의 서브쿼리