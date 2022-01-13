package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
public class TempController {

    //  1번 방법~~
//    @RequestMapping("/temp")

    //  2번 방법으로, (GET 방식은 X) URL 구조를 파악해서 슬래쉬(/) 사이의 값들을 parameter 로 처리할 떄
    @RequestMapping("/temp/{code}/{id}")
   public String temp(
            //  1번
            // 밑에서 value 는 get 방식으로 들어오는 코드의 parameter를 뜻함.
            // temp.html에서 name = "code" 의미
            // String type 의 code 로 사용하겠다.
            //  http://localhost:8080/temp?code=9999&id=100 방식으로 parameter 지정
//            @RequestParam(value = "code", defaultValue = "0") String code,
//            @RequestParam(value = "id", defaultValue = "0") int id

            //  parameter 가 많을 경우 밑에처럼 HashMap 을 사용할 수도 있지만 명시적이지 않은 방법이기 때문에 가급적 사용하지 않음. 강사님 평 정말 나쁜 방법!
//            @RequestParam HashMap<String, String> hashMap

            //  만약 정 parameter 가 많다고 하면 BoardVO 파일을 만들어서 parameter 와 getter, setter 작성 후
            //  ModelAttribute 어노테이션을 사용해서 VO 전체를 지정하기
//            @ModelAttribute BoardVO boardVO

            //  2번
            //  PathVariable 어노테이션은 URI의 패턴에 구분자로 들어오는 값을 처리할 때 사용
            //  http://localhost:8080/temp/9999/100 방식으로 parameter 지정
            //  RESTAPI 할 때 별도로 다룰 예정
            @PathVariable String code, @PathVariable int id

    ) {

        System.out.println("=======");

        //  1번 RequestParam, 2번 PathVariable
        System.out.println(code);
        System.out.println(id);

        //  HashMap
//        System.out.println(hashMap.get("code"));
//        System.out.println(hashMap.get("id"));

        //  ModelAttribute
//        System.out.println(boardVO.getTitle());
//        System.out.println(boardVO.getContent());

        System.out.println("=======");

        return "temp";

    }
}


