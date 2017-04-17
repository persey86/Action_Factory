package com.department.validation;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;

import java.util.regex.Pattern;

/**
 * Created  on 14.04.17.
 */
public class NoRussianLettersChek extends AbstractAnnotationCheck<NotRussisanLetters>{
    private static final String FULL_NAME_PATTERN = "[a-zA-Z0-9_]+$";

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate,
                               OValContext context, Validator validator) throws OValException {
        if (Pattern.compile(FULL_NAME_PATTERN).matcher(valueToValidate.toString()).matches())
return true;
       else return false;
    }
}
