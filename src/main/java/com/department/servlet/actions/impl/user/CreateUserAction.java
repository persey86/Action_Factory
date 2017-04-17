package com.department.servlet.actions.impl.user;

import com.department.exceptions.AppValidationException;
import com.department.exceptions.DepartmentRepositoryException;
import com.department.exceptions.UserRepositoryException;
import com.department.models.User;
import com.department.repository.impl.DepartmentRepositoryImpl;
import com.department.repository.impl.UserRepositoryImpl;
import com.department.servlet.actions.Action;
import com.department.servlet.actions.actionresults.ForwardResult;
import com.department.servlet.actions.actionresults.RedirectResult;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 06.04.17.
 */
public class CreateUserAction implements Action {
    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws UserRepositoryException, DepartmentRepositoryException {

        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
        String nameUser = request.getParameter("userName");
        String surnameUser = request.getParameter("userSurname");
        String emailUser = request.getParameter("userEmail");
        Integer userAge = Integer.parseInt(request.getParameter("userAge"));
        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
        boolean userEmailValid = emailUser.matches("^[a-z0-9_\\-.]{2,}@[a-z0-9_\\-.]{2,}\\.[a-z]{2,}$");

        User user = new User();
        user.setName(nameUser);
        user.setSurname(surnameUser);
        user.setEmail(emailUser);
        user.setAge(userAge);
        user.setCreated(new Date());
        user.setDepartmentId(departmentId);

        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(user);

        try {
            Map<String,String> allErrors = new HashMap<>();
            if (violations.size()>0){
                for (ConstraintViolation violation : violations) {
                    OValContext context = violation.getContext();

                    if (context instanceof FieldContext) {
                        Field field = ((FieldContext) context).getField();
                        String fieldName = field.getName();
                        String value = violation.getMessage();
                        allErrors.put(fieldName, value);
                    }
                }


//
//            if (nameUser.length()==0){
//                allErrors.put("hasEmptyFieldName", "field name can be fill");
//            }
//            if (surnameUser.length()==0){
//                allErrors.put("hasEmptyFieldSurname", "field surname can be fill");
//            }
//            if (emailUser.length()==0) {
//            allErrors.put("hasEmptyFieldEmail", "field e-mail can be fill");
//            }
//            if (emailUser.length() < 5) {
//                allErrors.put("hasShortLengthEmail", "User should have email with length more then 5 letters");
//            }
//            if (userRepositoryImpl.isAnyUsersHasThisName(emailUser)) {
//                allErrors.put("someUserHasAlreadyThisEmail", "Another user has this email");
//            }
//            if (!userEmailValid) {
//                allErrors.put("formatEmailUncorrected", "Inserted e-mail format uncorrectable");
//            }
//            if (ageUser <= 0){
//                allErrors.put("userAgeUncorrected", "Age of user must be positive");
//            }

            //  add dep id to user
 //           if (!allErrors.isEmpty()){
                throw new AppValidationException(allErrors);
            }

            userRepositoryImpl.save(user);

            return new RedirectResult("/allUsers?id=" +  departmentId);
        }catch (AppValidationException e){

            Map<String, String> mapErr = e.getMapErr();
            request.setAttribute("mapErr", mapErr);

            User invalidUser = new User();

            invalidUser.setName(nameUser);
            invalidUser.setSurname(surnameUser);
            invalidUser.setEmail(emailUser);
            invalidUser.setAge(userAge);
            invalidUser.setDepartmentId(departmentId);

            DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl();
            request.setAttribute("allDepartments", departmentRepositoryImpl.findAll());
            request.setAttribute("invalidUser", invalidUser);


            return new ForwardResult("createUser");

        }
    }
}
