package com.department.services.impl;

import com.department.exceptions.AppException;
import com.department.exceptions.AppValidationException;
import com.department.models.Department;
import com.department.repository.DepartmentRepository;
import com.department.repository.impl.DepartmentRepositoryImpl;
import com.department.services.DepartmentService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 15.04.2017.
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    private Validator validator;

    public DepartmentServiceImpl(){
        this.departmentRepository = new DepartmentRepositoryImpl();
        this.validator = new Validator();
    }

    @Override
    public List<Department> findAllEntities() throws AppException {
        return departmentRepository.findAll();
    }

    @Override
    public Department findOneEntity(Integer id) throws AppException {
        return departmentRepository.findOne(id);
    }

    @Override
    public Department saveEntityWithValidation(Department entity) throws AppException {
        validate(entity);
        return departmentRepository.save(entity);
    }

    @Override
    public void deleteEntityWithValidation(Integer id) throws AppException {
        departmentRepository.delete(id);
    }

    private void validate(Object entity) throws AppValidationException {

        List<ConstraintViolation> violations = this.validator.validate(entity);
            Map<String,String> valid = new HashMap<>();

            if (violations.size()>0) {
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
