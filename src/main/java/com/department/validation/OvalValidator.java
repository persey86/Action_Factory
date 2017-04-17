package com.department.validation;

import com.department.exceptions.AppValidationException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created  on 14.04.17.
 */
public class OvalValidator {

    private Validator validator = new Validator();

    /*public OvalValidator() {
        AnnotationsConfigurer myConfigurer = new AnnotationsConfigurer();
        myConfigurer.addCheckInitializationListener(SpringCheckInitializationListener.INSTANCE);
        Validator validator = new Validator();
    }*/
    public void validate(Object object) throws AppValidationException {

        List<ConstraintViolation> violations = validator.validate(object);

        Map<String, String> map = new HashMap<>();

        if (violations.size() > 0) {
            for (ConstraintViolation violation : violations) {
                OValContext context = violation.getContext();
                if (context instanceof FieldContext) {
                    Field field = ((FieldContext) context).getField();
                    String fieldName = field.getName();
                    String value = violation.getMessage();
                    map.put(fieldName, value);
                }
            }
            throw new AppValidationException(map);
        }
    }
}
