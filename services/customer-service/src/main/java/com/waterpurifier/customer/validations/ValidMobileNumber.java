package com.waterpurifier.customer.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MobileNumberValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMobileNumber {

    String message() default "must be a valid mobile number in E.164 format, for example +919876543210";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /** Optional country prefix restriction, for example +91 for India. Empty means any country. */
    String countryCode() default "";

}
