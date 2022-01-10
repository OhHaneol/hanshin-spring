# Spring

--------------

1. [Chapter 01](#Chapter 01)
2. [Chapter 02](#Chapter 02)
3. [Chapter 03](#Chapter 03)


---

## Chapter 01
###


---

## Chapter 02
### 1. Annotation
#### Annotation이란?
주석이다! 그런데 스프링에서 주석이란 프로그램 실행 시 특정한 기능을 실행하게 해준다.

#### Annotation 종류
1. @Controller : 웹애플리케이션에서 URL을 요청하였을 경우 응답을 처리 할 수 있도록 지정한다.
2. @RequestMapping : 요청한 URL이 어떤 메서드와 연결되고, 어떤 형태의 요청을 처리할 것인지 지정한다.

   @RequestMapping 어노테이션에는 URL이 1개 이상일 경우 쉼표로 구분해서 작성한다.

   - @RequestMapping(value = “/index”)

   - @RequestMapping(”/index”)

   - @RequestMapping(”/index”, “/main”)

   요청 방식인 GET, POST 등을 지정한다.

    - @RequestMapping(”/index”, method=RequestMapping.GET)

      GET의 경우 사용자가 직접 URL을 치고 들어가도 됨.(localhost:8080/~ 정상 작동)

    - @RequestMapping(”/index”, method=RequestMapping.POST)

      사용자가 직접 URL 치고 들어가면 에러가 남. (Whitelabel Error Page)

    @RequestMapping의 GET 또는 POST의 내용을 @GetMapping, @PostMapping 등으로 사용해도 동일하다.


---
