### 간단한 CRUD

* 직접 실습하고 기억에 남은 방식으로  작성하였습니다. 항상 더 좋은 방법을 찾아보겠습니다.

## CREATE

### store

```react
@observable books = Books;
@observable setBook = {}

//e.target.value 이벤트 조작
@action
    setBookProps(name,value){
        this.setBook ={
            ...this.setBook,
            [name] : value //서로다른 name에 값이 할당된다.
        }
    }
    
@action
	writeAdd(book){
        this.books.push(book); //container에서 받은 배열을 추가한다.
    }
```

### container

```react
bookvalue = (name,value) =>{
        this.props.TestStore.setBookProps(name,value)
    }
    onWriteAdd =() =>{
        let book = [
            {
                id: generateId(3),
                title: this.props.TestStore.setBook.title,
                content: this.props.TestStore.setBook.content,
            }
        ]
        console.log(book)
        this.props.TestStore.writeAdd(book);
    }
```



## Read

### list

```react
const bookList = books.map((book,index) => (
    <MainItem 
        key={index}
        book={book}
))
```



## Remove

* Modal을 사용하기 위해 Item 컴포넌트는 함수형 컴포넌트로 진행하였다.

### Store

```react
@action
    writeRemove(id){
        this.books = this.books.filter((book) => book.id !== id)
}
```

### Item

```react
function MainItem({ book, onWriteRemove}) {
    //함수형 컴포넌트를 사용할 때 각각의 book에 있는 내용을 사용하기 위해 비구조 할당을 사용하면 코드가 더 깔끔해진다.
    const {id, title, content} = book;
/**************************************/
<Button
    content="Remove"
    color='red'
    labelPosition='right'
    icon='checkmark'
	onClick={() => onWriteRemove(id)}
 />
```

