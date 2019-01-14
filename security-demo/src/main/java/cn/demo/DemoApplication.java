package cn.demo;

import cn.demo.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }

    @RequestMapping("/name")
    public String Text(){
         return  "helloword!";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        System.out.print(id);
        return  new User();
    }
}
