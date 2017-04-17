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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class EditUserAction implements Action {
    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws DepartmentRepositoryException, UserRepositoryException {

        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
User user = new User();
        Integer userId = Integer.parseInt(request.getParameter("userId"));

        String nameUser = request.getParameter("userName");
        String surnameUser = request.getParameter("userSurname");
        String emailUser = request.getParameter("userEmail");

        Integer ageUser = Integer.parseInt(request.getParameter("userAge"));
        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));

        boolean userEmailValid = emailUser.matches("[a-z0-9_\\-.]{2,}@[a-z0-9_\\-.]{2,}\\.[a-z]{2,}$");

        user.setId(userId);
        user.setName(nameUser);
        user.setSurname(surnameUser);
        user.setEmail(emailUser);
        user.setAge(ageUser);
        user.setCreated(new Date());
        user.setDepartmentId(departmentId);

        try {
            Map<String,String> errorsEditionUser = new HashMap<>();


            if (!userEmailValid){
                errorsEditionUser.put("badFormatEmail", "Inserted e-mail format uncorrectable");
            }
            if (emailUser.length()==0){
                errorsEditionUser.put("emptyEmailField", "field e-mail can be fill");
            }
            if (nameUser.length()==0){
                errorsEditionUser.put("emptyNameField","field name can be fill");
            }
            if (surnameUser.length()==0){
                errorsEditionUser.put("emptySurnameField","field surname can be fill");
            }
            if (emailUser.length() < 5) {
                errorsEditionUser.put("hasShortLengthEmail","User should have email with length more then 5 letters");
            }
            if (userRepositoryImpl.isAnotherUserHasThisEmail(userId, emailUser)) {
                errorsEditionUser.put("someUserHasThisEmail", "Another user has this email");
            }
            if (ageUser <= 0){
                errorsEditionUser.put("userAgeUncorrected", "Age must be positive number");
            }

            if (!errorsEditionUser.isEmpty()){
                throw new AppValidationException(errorsEditionUser);
            }
            //  update user
            userRepositoryImpl.save(user);

            return new RedirectResult("/allUsers?id=" +  departmentId);

        }catch (AppValidationException e){
            Map<String,String> errEditUser = e.getMapErr();
            request.setAttribute("errEditUser", errEditUser);

            User invalidUser = new User();

            invalidUser.setId(userId);
            invalidUser.setName(nameUser);
            invalidUser.setSurname(surnameUser);
            invalidUser.setEmail(emailUser);
            invalidUser.setAge(ageUser);
            invalidUser.setDepartmentId(departmentId);

            DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl();
            request.setAttribute("allDepartments", departmentRepositoryImpl.findAll());
            request.setAttribute("user", invalidUser);
            request.setAttribute("editUserError", e.getMessage());

            return new ForwardResult("editUser");

        }
    }
}