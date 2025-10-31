package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 0. Controller로 선언된 객체가 클라이언트의 요청을 받는다
public class HelloController {

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // ex) localhost:8080/hello-api?name=Chris
    @GetMapping("hello-api") // 1. localhost:8080으로 들어오는 요청에서
    @ResponseBody               // "/hello-api" 경로로 들어온 요청을 이 어노테이션이 붙은 메서드가 붙잡는다.
    public Hello helloApi(@RequestParam("name") String name) { // 2. 쿼리 파라미터로 날아오는 물음표 뒤의 name에 할당된 값을 받는다
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 3. 객체를 반환한다. -> MappingJackson2HttpMessageConverter가 동작하여 이 자바 객체를 반환할 때 json 객체로 치환하여 반환해준다.(기본 정책)
    }

    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}