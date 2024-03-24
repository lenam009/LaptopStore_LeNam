package com.lenam.laptopshop.service.validator.customAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuantityValidator implements
        ConstraintValidator<QuantityConstraint, Long> {

    @Override
    public void initialize(QuantityConstraint contactNumber) {
    }

    @Override
    public boolean isValid(Long contactField,
            ConstraintValidatorContext cxt) {
        if (contactField == null) {
            return false;
        }
        try {
            long d = Long.parseLong(contactField.toString());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
