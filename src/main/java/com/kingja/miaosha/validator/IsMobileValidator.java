package com.kingja.miaosha.validator;

import com.kingja.miaosha.util.ValidatorUtil;

import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Description:TODO
 * Create Time:2018/8/11 20:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
