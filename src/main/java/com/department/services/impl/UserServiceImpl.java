package com.department.services.impl;

import com.department.exceptions.AppException;
import com.department.exceptions.AppValidationException;
import com.department.models.User;
import com.department.repository.UserRepository;
import com.department.repository.impl.UserRepositoryImpl;
import com.department.services.UserService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 16.04.2017.
 */
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private Validator validator;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
        this.validator = new Validator();
    }

    @Override
    public List<User> findAllEntities() throws AppException {
        return userRepository.findAll();
    }

    @Override
    public User findOneEntity(Integer id) throws AppException {
        return userRepository.findOne(id);
    }

    @Override
    public User saveEntityWithValidation(User entity) throws AppException {
        validate(entity);
        return userRepository.save(entity);
    }

    @Override
    public void deleteEntityWithValidation(Integer id) throws AppException {
        userRepository.delete(id);
    }

    private void validate(Object entity) throws AppValidationException {

        List<ConstraintViolation> violations = this.validator.validate(entity);
        Map<String, String> valid = new HashMap<>();

        if (violations.size() > 0) {
            for (ConstraintViolation violation : violations) {
                OValContext context = violation.getContext();

                if (context instanceof FieldContext) {
                    Field field = ((FieldContext) context).getField();
                    String fieldName = field.getName();
                    String value = violation.getMessage();
                    valid.put(fieldName, value);
                }
            }
            throw new AppValidationException(valid);
        }
    }
}
