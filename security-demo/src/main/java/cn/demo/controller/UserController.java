package cn.demo.controller;


import cn.demo.dto.UserDTO;
import cn.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/*
   4种注解
   @RestController
   @RequestMapping(value = "/user",method = RequestMethod.GET)
   @RequestParam（require ， name ，value，defaultValue） // 设置必传参数
   @PageableDefault(page,size,sort)  // 分页对象

   @PathVariable // url 传参
   @jsonView 控制返回参数

   @RequestBody
   日期类型处理  传 时间戳  后台Date类型 -》 前端（自动变成时间戳） -传入时间戳-》  自动变成Date类型

   校验
   @Valid  开启校验

   BindingResult 将校验错误的数据放在BingingResult里面

   Hibernate Validator 提供校验注解 共同属性 例 @NotBlank(message = "密码不能为空")

   自定义注解 validtor
 */
@RestController
@RequestMapping("/user")
public class UserController {

    // 查找多条
    @GetMapping
    // 也可以传一个封装的类
    @JsonView(User.UserDetaView.class)
    public List<User> getUserList(/*@RequestParam(defaultValue = "24", required = false ,name = "userName") String userName,*/UserDTO userDTO, @PageableDefault(size = 15,page = 2,sort = {"userName","desc"}) Pageable pageable){
//        System.out.println("=========="+userName);
        System.out.println("=========="+ pageable.getPageNumber());
        System.out.println("=========="+ pageable.getPageSize());
        System.out.println("=========="+ pageable.getSort());

        List<User>  userArrayList = new ArrayList<User>();
        userArrayList.add(new User());
        userArrayList.add(new User());
        userArrayList.add(new User());
        return userArrayList;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserSimpView.class)
    public User getUser(@PathVariable Integer id){
        System.out.println("=========="+id);
        User user = new User();
        user.setUserName("lxp");
        user.setAge(12);
        return user;
    }

    @PostMapping
    @JsonView(User.UserSimpView.class)
    public User created(@Valid @RequestBody User user, BindingResult erros){

        if (erros.hasErrors()){
            erros.getAllErrors().stream().forEach(err -> System.out.println(err.getDefaultMessage()));
        }
        System.out.println("=========="+user.getUserName());
        user.setId(1L);
        return user;
    }

    @PutMapping("/{id}")
    @JsonView(User.UserSimpView.class)
    public User update(@Valid @RequestBody User user, BindingResult erros){

        if (erros.hasErrors()){
            erros.getAllErrors().stream().forEach(err ->{
                FieldError fieldError = (FieldError)err;
                System.out.println(fieldError.getDefaultMessage());
                System.out.println(fieldError.getField());

            });
        }
        System.out.println("=========="+user.getUserName());
        user.setId(1L);
        return user;
    }


}
