thunk 자체를 하나의 액션이라고 생각한다.

'book/loadBooks'는 타입이다.

```react
//BookSlice.tsx
export const getBook = createAsyncThunk(
  'book/loadBooks',
  async ({ keyword, offset, limit }: LoadBooksType) => {
    const response = await axios.get(
      `http://private-348740-dst2.apiary-mock.com/api/v1/books`
    );
    console.log(response.data);
    return response.data;
  }
);
```

```react
//bookList
const a = { keyword: '1', offset: 10, limit: 10 };
dispatch(getBook(a));
```

위와 같이 객체로 보내주는 이유는 뭘까??

FSA 를 생각해보자

```react
function addTodo(text) {
  return {
    type: 'ADD_TODO',
    payload: { text },
  }
}
```

객체로 데이터를 보내주는 이유는 type을 제외한 payload를 보내기 때문이다.

```react
//BookSlice.tsx
async ({ keyword, offset, limit }: LoadBooksType) => {})

//bookList
const a = { keyword: '1', offset: 10, limit: 10 };
dispatch(getBook(a));
```



createAsyncThunk 를 사용하면 extraReducer와 reducer  를 사용한다.

extraReducer는 thunk가 성공할 때, 진행 중일 때, 실패할 때 처리하는 리듀서를 작성할 수 있으며

reducer는 기본적인 리듀서를 구현하게 된다.