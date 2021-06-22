sort 함수

프로젝트 중에 mock 데이터를 다양한 형태로 정렬하다가 여러가지 방법이 있는 걸 알게 되었고, 다양한 sort 함수 사용법을 정리해 보고 싶어졌다.

sort 함수란

**배열의 요소**를 적절한 위치에 **정렬**한 후 그 **배열을 반환**한다.

기본 정렬 순서는 **문자열의 유니코드** 포인트를 따른다. (오름차순)

#### 기본 정렬

```javascript
const test = ['b','a','c','g','d']
test.sort()
console.log(test) // ['a','b','c','d','g']
```



#### compareFunction 파라미터

> 정렬 순서를 정의하는 함수

```javascript
const test = [10, 3, 8, 4, 1];
test.sort((a, b) => {
  console.log(a,b);
  return a - b;
});
/*
a : 3 b : 10
a : 8 b : 3
a : 8 b : 10
a : 8 b : 3
a : 4 b : 8
a : 4 b : 3
a : 1 b : 8
a : 1 b : 4
a : 1 b : 3
*/
console.log(test) // [1, 3, 4, 8, 10]
```

a - b가 0 보다 작은 경우 a가 b보다 앞에 있어야 한다.

a - b 가 0 과 같은 경우 a와 b의 순서를 바꾸지 않는다.

a - b 가 0 보다 클 경우 b가 a보다 앞에 있어야 한다.

##### 내림차순

> 내림차순 정렬은 b -a 로 바꿔주면 된다.



#### json 형태 정렬

> json 형태의 정렬은 
>
> **배열로 정렬하는 방법**과 **Object.entries()**를 사용하는 정렬이 있다. 

##### 배열로 정렬

```javascript
const testobj = { a: 5, b: 3, c: 1 };
const sortobj = [];
for (const number in testobj) {
	sortobj.push([number, testobj[number]]);
}
sortobj.sort(function (a, b) {
	return a[1] - b[1];
});
console.log(sortobj);
/*
[
  ["c", 1]
  ["b", 3]
  ["a", 5]
]
*/
```

##### **Object.entries()**

```javascript
const testobj = { a: 5, b: 3, c: 1 };
//value 값으로 정렬
const sort = Object.entries(testobj).sort(([, a], [, b]) => a - b); 

console.log(sort);
/*
[
  ["c", 1]
  ["b", 3]
  ["a", 5]
]
*/
```

```javascript
//Object.entries() 예제
const testobj = { a: 5, b: 3, c: 1 };

Object.keys(testobj) : // ["a", "b", "c"]
Object.values(testobj) : // [5, 3, 1]
Object.entries(testobj) : // [["a": 5], ["b": 3], ["c": 1]]
```

##### 배열 형태에서 다시 Json 형태로 돌리기

```javascript
const testobj = { a: 5, b: 3, c: 1 };
const sort = Object.entries(testobj)
  .sort(([, a], [, b]) => a - b)
  .reduce((r, [k, v]) => ({ ...r, [k]: v }), {});

console.log(sort);
// {c: 1, b: 3, a: 5}
```



#### object 정렬

```javascript
const test = [
    { key: 'a', value: 1 },
    { key: 'b', value: 5 },
    { key: 'c', value: 3 }
];
const sort = test.sort((a, b) => a.value - b.value);

console.log(sort);
/*
[
  {key: "a", value: 1}
  {key: "c", value: 3}
  {key: "b", value: 5}
]
*/
```

