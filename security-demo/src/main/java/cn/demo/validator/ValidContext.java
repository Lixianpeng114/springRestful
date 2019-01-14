package cn.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidContext implements ConstraintValidator<MyValid, Object> {

    // 初始化执行
    @Override
    public void initialize(MyValid myValid) {

    }

    // 验证逻辑错误返回false
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("userName"+o);
        return false;
    }
}
