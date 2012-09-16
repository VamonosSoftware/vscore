package com.vamonossoftware.core.validation;

/**
 *
 * @author paul
 */
public class MockValidationRule implements ValidationRule {
    private boolean result= false;
    protected boolean invoked = false;

    public MockValidationRule(boolean result) {
        this.result = result;
    }

    public Errors validate(Object input) {
        invoked = true;
        Errors errors = new Errors();
        if(!result) {
            errors.add(new ValidationError(this.getClass().getSimpleName(), "error"));
        }
        return errors;
    }

}
