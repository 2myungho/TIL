##### @RequestParam

> ```java
> RequestParam("가져올 데이터의 이름")[데이터타입][가져온 데이터를 담을 변수]
> ```
>
> ```java
> @GetMapping("hello-mvc")
> public String hellMvc(@RequestParam("name") String name, Model model){
>     //ex) 가져올 데이터의 이름은 주소창의 name = 1 일 때 name 변수에 1이 저장된다.
>     model.addAttribute("name", name);
>     return "hello-template";
> }
> ```



##### @ResponseBody

>Http body부에 직접 return 데이터를 넣어주겠다는 의미

