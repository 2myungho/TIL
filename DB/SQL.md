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



### 순위 매기기 함수

##### RANK

> **중복 값**들에 대해서 **동일 순위**로 표시합니다.
>
> 중복 순위 다음 값에 대해서는 **중복 개수만큼 떨어진 순위로 출력**하도록 하는 함수입니다.
>
> ```SQL
> RANK() OVER (ORDER BY salary DESC) RANK등수
> ```
>
> EX) 1, 2, 2, 2, 5, 6 ......

##### DENSE_RANK

> **중복 값**들에 대해서 **동일 순위**로 표시합니다.
>
> 중복 순위 다음 값에 대해서는 **중복 값 개수와 상관 없이 순차적인 순위 값을 출력**하도록 하는 함수입니다.
>
> ```SQL
> DENSE_RANK() OVER (ORDER BY salary DESC) DENSE_RANK등수
> ```
>
> EX) 1, 2, 2, 2, 3, 4, 5 ....

##### ROW_NUMBER

> **중복 값**들에 대해서도 **순차적인 순위를 표시**하도록 출력하는 함수입니다.
>
> ```SQL
> ROW_NUMBER() OVER (ORDER BY salary DESC) ROW_NUMBER등수
> ```
>
> EX) 1, 2, 3, 4, 5, 6 ..... 



##### OVER절

> ```SQL
> OVER (PARTITION BY emp_no ORDER BY salary DESC)
> ```
>
> **PARTITION**
>
> > **그룹 내 순위 및 그룹 별 집계**를 구할 때 유용하게 사용할 수 있습니다.
> >
> > EX) 중복 ID 내에서 순위를 매길 수 있다.

##### 오름차순(ASC) 

> 1 2 3 4 5 6

##### 내림차순(DESC)

> 6 5 4 3 2 1



##### 예제 풀이

```SQL
-- HeidiSQL 프로그램을 사용하여 풀이
SELECT e.emp_no, e.first_name, e.last_name, e.gender, e.hire_date, dept.dept_name ,t.title, s.salary AS max_salary
from employees e

-- 부서이름(dept_name) JOIN
INNER JOIN (
	SELECT c.emp_no, d.dept_name
 	FROM dept_emp c
 	INNER JOIN departments d
 	ON c.dept_no = d.dept_no
 	ORDER BY c.emp_no ASC
) dept
ON e.emp_no = dept.emp_no

-- 직급(title) JOIN
INNER JOIN titles t
ON dept.emp_no = t.emp_no

--최대 급여(max_salary) JOIN
INNER JOIN (
	SELECT *
	FROM (
		SELECT
			emp_no
			, salary
			, ROW_NUMBER() OVER (PARTITION BY emp_no ORDER BY salary DESC) AS RankNo
		FROM salaries
	) rank
    -- 같은 emp_no 중에 salary가 가장 큰 데이터 출력
	WHERE RankNo = 1
) s
ON t.emp_no = s.emp_no

--2000년 이후 고용된 종업원들을 대상으로 한다. (hire_date)
WHERE e.hire_date >= '20000101';
```

> HeidiSQL 프로그램을 사용하여 풀이했습니다.