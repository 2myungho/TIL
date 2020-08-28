# MongoDB
## 8/27
* ### NoSQL의 MongoDB
1. 관계형 데이터 베이스의 한계를 극복하기 위해 만들어진 데이터 베이스이다.
2. MongoDB는 Json 형태로 되어 있다.
3. mongo DB의 DataBase는 Collection의 집합.
4. Collection은 document의 집합.
* ### quiry 예제 직접 풀이

  1. employee_db 생성 **//DB 생성**

     `use my_mongo`

  2. employees **Collection** 생성

     `db.createCollection("employees")`

  3. employees Collection capped 확인

     `db.employees.isCapped()`

  4. employees 컬렉션 statistics 확인

     `db.employees.stats()`

  5. Document 추가 insertMany() 사용

     ```
     db.employees.insertMany([
     {"number":1001,"last_name":"Smith","first_name":"John","salary":62000,"department":"sales", hire_date:ISODate("2016-01-02")},
     {"number":1002,"last_name":"Anderson","first_name":"Jane","salary":57500,"department":"marketing", hire_date:ISODate("2013-11-09")},
     {"number":1003,"last_name":"Everest","first_name":"Brad","salary":71000,"department":"sales", hire_date:ISODate("2017-02-03")},
     {"number":1004,"last_name":"Horvath","first_name":"Jack","salary":42000,"department":"marketing", hire_date:ISODate("2017-06-01")}
     ])
     ```

  6. document select all

     `db.employees.find()`

  7. SELECT * FROM employees WHERE department='sales';

     `db.employees.find({department:"sales"})`

  8. select * from employees where hire_date >= "2017-01-01"

     `db.employees.find({hire_date:{$gte:ISODate("2017-01-01")}})`

  9. select number,last_name,first_name from employees

     `db.employees.find({},{number:1,last_name:1,first_name:1,_id:0})`

  10. select number,last_name,first_name from employees where number=1003

      `db.employees.find({number:1003},{number:1,last_name:1,first_name:1,_id:0})`

  11. select * from employees where number = 1001 and department = 'sales'

      `db.employees.find({number:1001,department:"sales"})`

  12. select * from employees where number = 1002 or department = 'sales'

      `db.employees.find({$or:[{number:1002},{department:"sales"}]})`

  13. select * from employees where number in (1001,1003)

      `db.employees.find({number:{$in:[1001,1003]}})`

  14. select * from employees where number not in (1001,1003)

      `db.employees.find({nunber:{$nin:[1001,1003]}})`

  15. select * from employees where last_name like '%e%'

      `db.employees.find({last_name:{$regex: /e/}})`

  16. select * from employees where first_name like '%a%'

      `db.employees.find({first_name:{$regex:/a/}})`

  17. select * from employees where first_name like 'B%'

      `db.employees.find({first_name:{$regex:/B/}})`

  18. select * from employees where last_name like '%h'

      `db.employees.find({last_name:{$regex:/h/}})`

  19. select * from employees order by department

      `db.employees.find().sort({department:1})`

  20. select * from employees order by hire_date desc

      `db.employees.find().sort({department:-1})`

  21. select count(*) from employees

      `db.employees.count()`

  22. insertOne

      ```
      //예제
      //insert into employees (number,last_name,first_name,salary,department,status) values (1005,'Hong','Gildong',55000,'clerk','A')
      //insert into employees (number,last_name,first_name,salary,department,status) values (1006,'박','둘리',50000,'clerk','B')
      ```

      ```
      db.employees.insertMany([
      {number:"1005",last_name:"Hong",first_name:"Gildong",salary:55000,department:"clerk",status:"A"},
          {number:"1006",last_name:"박",first_name:"둘리",salary:50000,department:"clerk",status:"B"}
      ])
      ```

      

  23. select * from employees where status = 'A'

      `db.employees.find({status:"A"})`

  24. select * from employees where status in ('A','B))

      `db.employees.find({status:{$in:["A","B"]}})`

  25. status column이 존재하는 document 조회

      `db.employees.find({status:{$exists:true}})`

  26. status column이 존재하지 않는 document 조회

      `db.employees.find({status:{$exists:false}})`

  27. hire_date column이 존재하는 document 조회

      `db.employees.find({hire_date:{$exists:true}})`

  28. hire_date column이 존재하지 않는 document 조회

      `db.employees.find({hire_date:{$exists:false}})`

  29. status column이 존재하는 document count 조회

      `db.employees.count({status:{$exists:true}})`

  30. hire_date column이 존재하는 document count 조회

      `db.employees.count({hire_date:{$exists:true}})`

  31. select distinct(department) from employees

      `db.employees.aggregate([{$group:{_id:"$department"}}])`

  32. select * from employees where salary >= 50000

      `db.employees.find({salary:{$gt:50000}})`

  33. select * from emploees where salary < 50000

      `db.employees.find({salary:{$lte:50000}})`

  34. select * from employees where salary > 45000 and salary <= 60000

      `db.employees.find({salary:{$gt:45000,$lte:60000}})`

  35. update employees set salary = 57000 where number = 1005

      `db.employees.updateMany({number:1005},{$set:{salary:57000}})`

  36. update employees set last_name = '홍' where number = 1005

      `db.employees.updateMany({number:1005},{$set:{last_name:"홍"}})`

  37. update employees set salary = salary + 100 where number in (1005,1006)

      `db.employees.updateMany({number:{$in:["1005","1006"]}},{$inc:{salary:100}})`

  38. delete from employees where status = 'A'

      `db.employees.deleteMany({status:"A"})`

  

  `db.employees.find()`

  

## 8/28

* ### quiry 예제 직접 풀이

  * collection 은 zips_db
  * $group, $metch, $project, $limit 사용
    * **$group**: 
      1. 그룹에 대한 _id를 지정해주어야 한다. 
      2. 특정 필드에 대한 집계 연산을 가능하게 해준다.
    * **$metch**: 
      1. sql 조건의만족하는 document만 필터링해서 출력한다.
      2. sql의 where, having과 유사하다.
      3. metch를 group 앞에 쓰면 where로 사용할 수 있고, 뒤에 쓰면 having으로 쓸 수 있다.
    * **project**: 
      1. _id에 그룹핑 된 특정 필드를 부분적으로 출력할 수 있다. 
    * **limit** :
      1. 원하는 만큼의 데이터 수만 출력할 수 있다.

  1. SQL: SELECT COUNT(*) AS count FROM zip

     ```
     db.zips_col.aggregate([
         {
             $group:{
                 _id:null,
                 count:{$sum:1}
             }
         }
     ])
     ```

  2. SQL: SELECT SUM(pop) as total_pop AS count FROM zip

     ```
     db.zips_col.aggregate([
         {
             $group:{
                 _id:null,
                 total_pop:{$sum:"$pop"}
             }
         }
     ])
     ```

  3. SQL: SELECT state, SUM(pop) as total_pop FROM zip GROUP BY state

     ```
     db.zips_col.aggregate([
         {
             $group:{
                _id:"$state",
                total_pop:{$sum:"$pop"}
             }
         }
     ])
     ```

  4. SQL : select city, sum(pop) as total_pop from zip group by city

     ```
     db.zips_col.aggregate([
         {
             $group:{
                 _id:"$city",
                 total_pop:{$sum:"$pop"}
             }
         }
     ])
     ```

  5. SQL: SELECT state, SUM(pop) as total_pop FROM zip GROUP BY state ORDER BY as

     ```
     db.zips_col.aggregate([
         {
             $group:{
                 _id:"$state",
                 total_pop:{$sum:"$pop"}
             }
         },
         {
             $sort:{total:1}
         }
     ])
     ```

  6. SQL: SELECT COUNT(*) FROM zip WHERE state = 'MA'

     ```
     db.zips_col.aggregate([
         {
             $match:{state:"MA"}
         },
         {
             $group:{
                 _id:null,
                 count:{$sum:1}
             }
         }
     ])
     ```

  7. select state,sum(pop) as total_pop from zip where state = 'MA' group by state

     ```
     db.zips_col.aggregate([
         {
             $match:{state:"MA"}
         },
         {
             $group:{
                 _id:"$state",
                 total_pop:{$sum:"$pop"}
             }
         }
     ])
     
     //7.1 select sity,sum(pop) as total_pop from zip where state = 'MA' group by state
     db.zips_col.aggregate([
         {
             $match:{state:"MA"}
         },
         {
             $group:{
                 _id:"$city",
                 total_pop:{$sum:"$pop"}
             }
         }
     ])
     
     //7.2select state,sum(pop) as total_pop from zip where state in ('DE', 'MS') group by state
     db.zips_col.aggregate([
         {
             $match:{state:{$in:["DE","MS"]}}
         },
         {
             $group:{
                 _id:"$state",
                 total_pop:{$sum:"$pop"}
             }
         }
     ])
     ```

  8. SELECT state, SUM(pop) as total_pop FROM zip GROUP BY state HAVING SUM(pop) > 10000000

     ```
     db.zips_col.aggregate([
         {
             $group:{
                 _id:"$state",
                 total_pop:{$sum:"$pop"}
             }
         },
         {
             $match:{total_pop:{$gte:10000000}}
         }
     ])
     ```

  9. 1000만 이상의 state 별 총 인구를 state_pop 필드명으로 출력하고 _id는 출력하지 않기
     // 5개 건수만 출력한다.

     ```
     db.zips_col.aggregate([
         {$group:{_id:"$state",state_pop:{$sum:"$pop"}}},
         {$match:{state_pop:{$gte:10 * 1000*1000}}},
         {$project:{_id:0,state_pop:1}},
         {$limit:5}
     ])
     
     //9.1 NY state의 city별 총 인구수를 city_pop 필드명으로 출력하고 _id는 출력하지 않는다.
     //5건만 출력하세요
     db.zips_col.aggregate([
         {$match:{state:"NY"}},
         {$group:{_id:"$city", city_pop:{$sum:"$pop"}}},
         {$project:{_id:0,city_pop:1}},
         {$limit:5}
     ])
     ```

  10. 1000만 이상의 state만 내림차순 정렬하여 3개만 가져오기
      //state 별 인구수의 합계가 1000만 이상

      ```
      db.zips_col.aggregate([
          {
              $group:{
                  _id: "$state",
                  state_pop:{$sum:"$pop"}
              }
          },
          {
              $match:{state_pop:{$gt:10000000}}
          },
          {
              $sort:{state_pop:-1}
          },{
              $limit:3
          }
      ])
      ```

  11. 1000만 이상의 state만 내림차순 정렬하여 3개만 가져오기
      //state 별 인구수의 합계가 1000만 이상,
      //_id는 출력하지 않고, 인구수만 출력한다.
      //$group, $match, $sort, $project, $limit

      ```
      db.zips_col.aggregate([
          {$group:{_id:"$state", state_pop:{$sum:"$pop"}}},
          {$match:{state_pop:{$gt:10000000}}},
          {$sort:{state_pop:-1}},
          {$project:{_id:0,state_pop:1}},
          {$limit:3}
      ])
      ```

  12. select state, city, sum(pop) as total_pop from zip group by state,city

      ```
      db.zips_col.aggregate([
          {
              $group:{
                  _id:{
                      state:"$state",
                      city:"$city"
                  },
                  total_pop:{$sum:"$pop"}
              }
          }
      ])
      
      //_id를 제외하고 출력
      db.zips_col.aggregate([
          {$group:{_id:{state:"$state",city:"$city"},total_pop:{$sum:"$pop"}}},
          {$project:{"_id.state":1, total_pop:1}}
      ])
      ```

  13. select state, city, sum(pop) as total_pop from zip
      //GROUP BY state, city HAVING city = 'POINT BAKER'

      ```
      db.zips_col.aggregate([
          {
              $group:{
                  _id:{
                      state:"$state",
                      city:"$city"
                  },
                  total_pop:{$sum:"$pop"}
              }
          },
          {
              $match:{"_id.city":"POINT BAKER"}
          }
      ])
      ```

  14. SELECT AVG(pop) FROM zip GROUP BY state, city

      ```
      db.zips_col.aggregate([
        {
          $group:{
              _id:{
                state:"$state",
                city:"$city"
              },
              avg_pop:{$avg:"$pop"}
           }
          }
        ])
      
      //전체 평균
       db.zips_col.aggregate([
          {
              $group:{
                  _id:null,
                  avg_pop:{$avg:"$pop"}
              }
          }
        ])
      ```

  15. select state,city, avg(pop) as avg_pop from zip
      //GROUP BY state, city having avg_pop > 30000
      //주별 도시 인구 평균이 30000이 넘는 곳의
      //state 와 city 이름만 출력하고 평균을 출력하지 않기 (3개만 출력하기)
      //$group, $match, $project, $limit

      ```
      db.zips_col.aggregate([
          {
              $group:{
                  _id:{
                     state:"$state",
                     sity:"$city"
                  },
                  avg_pop:{$avg:"$pop"}
              }
          },
          {$match:{avg_pop:{$gte:30000}}},
          {$project:{avg_pop:0}},
          {$limit:3}
      ])
      ```