package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //MVC: Model, View, Controller
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //viewResolver에게 던진다. (Thymeleaf 템플릿 엔진 처리)
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name, Model model){
        return "hello "+ name; //"hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){ //이방식은 JSON 방식
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

// GetMapping  : html GET 방식전달
// ResponseBody : http의 body에 문자 내용을 직접 넣겠다.
// ResponseBody를 사용하고 객체를 반환하면 객체가 JSON으로 변환된다.
// JSON은 Key와 Value로 이루어져 있다. , 최근에는 JSON으로 반환하는것이 기본적이다.
// ResponseBody를 사용시 기본 문자 처리시에는 StringHttpMessageConverter가 동작, 기본 객체 처리시에는 MappingJackson2HttpMessageConverter가 동작

// 정적 컨텐츠 : 그냥 파일을 그대로 내려준다.
// MVC와 템플릿 엔진 : 템플릿 엔진을 모델뷰 컨트롤 방식으로 쪼개서 뷰를 템플릿 엔진으로 html을 프로그래밍적으로 랜더링 해서, 랜더링이 된 html을 클라이언트에게 전달해준다.
// API : 객체를 반환하는 방식 , HttpMessageConverter을 통해서 JSON으로 바꿔서 반환해주는 것이다.