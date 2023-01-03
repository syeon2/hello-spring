package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /*
    - 웹 브라우저 ->
    - localhost:8080/hello ->
    - Spring Boot - 내장 톰켓 서버 ->
    |             |
    |             | Spring Container |- helloController -> return: hello, model(data: hello!!) ->
    -             |                  |- return 값이 String 일 경우 : viewResolver -> templates/hello.html(Thymeleaf 템플릿 엔진 처리) ->
                                     |- return 값이 Object 일 경우 : HttpMessageConverter / JsonConverter, StringConverter ->
    - hello.html (변환 후) / {name: spring} ->
    - 웹 브라우저
     */

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
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
