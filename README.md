# Spring

1. [Chapter 01](#Chapter01)
2. [Chapter 02](#Chapter02)
3. [Chapter 03](#Chapter03)



## Chapter 01
###



## Chapter 02
### 1. Annotation
#### Annotation이란?
주석. 그런데 일반 코드가 아닌 스프링에서 주석이란 프로그램 실행 시 특정한 기능을 실행하게 해준다.

- #### Annotation 종류
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


### 2. Thymeleaf 와 HTML
- #### 타임리프

- #### jquery
  1. jquery.com 에서 3.6.0 버전을 받아서 내용을 복사 후 resources.static.js.jquery-3.6.0.min.js 파일에 붙여넣기.
  2. 그런 다음 html 문서에서 script 태그로 작성 후 다시 application 을 run 하면 console 에 jquery를 찾지 못해서 404 error 를 띄움.
  3. 이 경우 resources.application.properties 파일에 관련 내용을 작성하면 해결!
  4. application.properties 파일은 데이터베이스 접속에 대한 정보와 스프링 프로젝트에 대한 모든 정보를 기술하는 파일.


### 3. 데이터 송수신

- #### MVC 아키텍처(Model - View - Controller)
  - Model: 데이터를 조회하고 결과를 생성하며 Service 와 DAO 로 나뉘어짐.
    - Service는 비즈니스 로직으로, 여러 Controller 단에서 재사용성이 있는 코드를 Service 단에서 처리하도록 한다.
    - DAO 는 데이터베이스에 직접 접근하기 위한 파트이다.
  - View: 화면을 출력.
  - Controller: 모델과 뷰를 제어.
- #### HTTP 프로토콜
  - 웹 브라우저와 웹 서버간 통신 규약.
- #### Annotation part 2