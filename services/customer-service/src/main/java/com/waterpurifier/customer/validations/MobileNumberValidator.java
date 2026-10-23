package com.waterpurifier.customer.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class MobileNumberValidator implements ConstraintValidator<ValidMobileNumber, String> {
    /** E.164: a plus sign followed by 8 to 15 digits, first digit 1-9. */
    private static final Pattern E164 = Pattern.compile("^\\+[1-9]\\d{7,14}$");

    private String countryCode;


    @Override
    public void initialize(ValidMobileNumber annotation) {
       this.countryCode = annotation.countryCode()==null?"":annotation.countryCode();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isBlank()) {
            return true;
        }
        String candidate = value.replace(" ", "").replace("-", "");
        if (!E164.matcher(candidate).matches()) {
            return false;
        }
        return countryCode.isEmpty() || candidate.startsWith(countryCode);

    }
}
