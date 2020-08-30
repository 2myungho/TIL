# Oracle

## 8/29

* ### REBMS (관계형 데이터베이스)

  * **테이블**
    * 가장 기본이 되는 객체, 데이터 저장소이다.
    * ROW와 column 으로 구성되어 있다.

  * **표현식**
    * select
    * WHERE, HAVING 조건절
    * ORDER BY, CONNECT BY, START WITH
    * INSERT문의 VALUES절
    * UPDATE 문의 SET절

* ### quiry 에제 직접 풀이

  1. EMP테이블에서 사원이름순으로 사원번호, 이름, 업무, 부서번호를 조회하시오(ORDER BY : 정렬 출력, 기본 오름차순).

     ```
     select EMPNO, ENAME, job, DEPTNO from emp order by ename desc;
     ```

  2. EMP테이블에서 가장 최근에 입사한 순으로 사원번호, 이름, 업무, 급여, 입사일자, 부서번호를 출력하라(ORDER BY : 정렬 출력 + DESC; 내림차순 정렬).

     ```
     select EMPNO, ename, job,sal, comm,(sal * 12 + comm) 급여,HIREDATE,DEPTNO from emp order by HIREDATE desc;
     ```

  3. null 값을 초기화 해주는 함수가 oracle : nvl(), mysql : ifnull()

     ```
     select EMPNO, ename, job,sal, comm,(sal * 12 + nvl(comm,0)) 급여,HIREDATE,DEPTNO from emp order by HIREDATE desc;
     ```

  4. 모든 사원의 이름 및 급여, 급여에 300을 더한 값을 출력하시오.

     ```
     select ename, (sal * 12 + nvl(comm,0)) "급 여", (sal * 12 + nvl(comm,0)) +300 급여300 from emp;
     ```

  5. emp 테이블에서 ENAME을 NAME 로, SAL을 SALARY로 출력하시오.

     ```
     select ename name, sal salary from emp;
     ```

  6. EMP 테이블에서 ENAME를 '성  명' 으로 sal *12 를 '급  여'로 출력하시오.

     ```
     select ename "성 명" , sal*12 "급 여" from emp;
     ```

  7. EMP 테이블에서 이름과 업무를 "KING is a PRESIDENT" 형식으로 출력하시오( ||'  '|| : 사용방법).

     ```
     --컬럼값과 static 문자열을 concat 하려면
     --oracle : ||, mysql : concat()
     select ename || ' is a ' || job "정 답" from emp;
     ```

  8. EMP 테이블에서 이름과 연봉을 "KING : 1 Year salary = 60000" 형식으로 출력하시오( ||'  '|| : 사용방법).

     ```
     select ename || ' : 1 Year salary = ' || (sal * 12 + nvl(comm,0)) 정답 from emp;
     ```

  9. EMP 테이블에서 SMITH 사원의 모든 정보를 조회하시오(조건식 : WHERE 절 사용방법).

     ```
     select *
         from EMP
      where ename = 'SMITH';
     
     select *
         from EMP
     where ename = upper('smith'); -- 대문자 변환
     ```

  10. EMP 테이블에서 급여가 3000이상인 사원의 사원번호, 이름, 담당업무, 급여를 출력하라(조건식 : WHERE 절 사용방법).

      ```
      select *
          from EMP
      where sal >= 3000;
      ```

  11. EMP 테이블에서 급여가 3000이상이고 job='analyst' 사원의 사원번호, 이름, 담당업무, 급여를 출력하라(조건식 : WHERE 절 사용방법).

      ```
      --and
      select *
          from EMP
      where sal >=3000
      and job = upper('analyst');
      ```

  12. EMP 테이블에서 담당업무가 Manager이거나 급여가 3000 이상인 사원의 정보를 사원번호, 성명, 담당업무, 급여, 부서번호를 출력하라(조건식 : WHERE 절 사용방법).

      ```
      select *
          from EMP
      where job = upper('Manager')
      or sal >=3000;
      ```

  13. EMP 테이블에서 1982년 1월 1일 이후에 입사한 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하라(조건식 : WHERE 절 사용방법).

      ```
      select *
          from EMP
      where HIREDATE > '1982/01/01';
      
      select ename, to_char(HIREDATE, 'yyyy-MM-DD')입사일자
          from EMP
      where HIREDATE > '1982/01/01';
      
      select distinct mgr from emp;
      --insert column name 설정하고
      insert into emp (empno,ename,job,mgr,hiredate,sal,DEPTNO)
      values (8000,'이명호','pro',7788,sysdate,5000,40);
      commit;
      
      --insert column name울 설정하지 않고
      insert into emp values (8001,'둘리','sales',7788,
                   to_date('11-01-2019','DD-MM-yyyy'),3500,10,40) ;
      commit;
      select  * from emp order by HIREDATE desc;
      ```

  14. EMP 테이블에서 급여가 1300에서 1500사이의 사원의 성명, 담당업무, 급여, 부서번호를 출력하라.

      ```
      select *
          from EMP
      where sal between 1300 and 1500;
      ```

  15. EMP 테이블에서 부서번호가 10,20인 사원의 모든 정보를 출력하는 SELECT 문장을 작성하시오. 단 이름순으로 정렬하시오.

      ```
      select *
          from EMP
      where DEPTNO in (10,20);
      ```

  16. EMP 테이블에서 사원번호가 7902, 7788, 7566 인 사원의 사원번호, 성명, 담당업무, 급여, 입사일자를 출력하여라.

      ```
      select *
          from EMP
      where EMPNO in (7902,7788,7566);
      ```

  17. EMP 테이블에서 JOB이 Manager,Clerk,Analyst가 아닌 사원의 사원번호, 성명, 담당업무, 급여, 부서번호를 출력하여라

      ```
      select *
          from EMP
      where job not in upper(('Manager'),'Clerk',upper('Analyst'));
      ```

  18. EMP 테이블에서 입사일자가 1982년도에 입사한 사원의 사번, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하여라.

      ```
      select  *
          from EMP
      where HIREDATE like '%82%';
      
      select * from EMP
      where HIREDATE between '1982-01-01' and '1982-12-31';
      ```

  19. EMP 테이블에서 보너스가 NULL인 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하여라.

      ```
      select * from emp where comm is null;
      ```

  20. EMP테이블에서 사원의 입사일이 1981년도 2월인 사원들의 모든 정보를 조회하시오.

      ```
      select  *
      from EMP
      where HIREDATE like '%81/02%'
      ```

  21. EMP 테이블에서 급여가 1100이상이고 JOB이 Manager인 사원의 사원번호, 성명, 담당업무, 급여, 입사일자, 부서번호를 출력하여라.

      ```
      select * from emp where sal >= 1100 and job = upper('manager');
      ```

  22. 업무가 PRESIDENT이고 급여가 1500이상이거나,업무가 SALESMAN인 사원의 사원번호, 이름, 업무, 급여를 출력하여라.

      ```
      select  * from emp where (job = 'PRESIDENT' and sal >= 1500) or job = 'SALESMAN'
      ```

  23. EMP 테이블에서 급여가 1500이상이고, 부서번호가 10 또는30인 사원의 이름과 급여를 출력하는 SELECT 문장을 작성하여라. 단 HEADING을 Employee과 Monthly Salary로 출력하여라.

      ```
      select * from emp where  sal >= 1500 and DEPTNO in (10,30);
      ```

## 8/30

### 서브쿼리(SubQuery)

* #### 메인쿼리를 구성하는 소단위의 쿼리

* #### 서브쿼리 자페는 일반 쿼리와 다를 바가 없다.

* #### 서브쿼리의 종류

  * ##### 인라인뷰 : FROM절에 위치

  *  ##### 스칼라 쿼리 : SELECT 절에 위치

