package com.department.servlet.actions.impl.user;

import com.department.exceptions.AppException;
import com.department.exceptions.AppValidationException;
import com.department.models.User;
import com.department.repository.impl.UserRepositoryImpl;
import com.department.services.DepartmentService;
import com.department.services.UserService;
import com.department.services.impl.DepartmentServiceImpl;
import com.department.services.impl.UserServiceImpl;
import com.department.servlet.actions.Action;
import com.department.servlet.actions.actionresults.ForwardResult;
import com.department.servlet.actions.actionresults.RedirectResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;


public class EditUserAction implements Action {

    private UserService userService;
    private DepartmentService departmentService;

    public EditUserAction(){
        this.userService = new UserServiceImpl();
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

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

            //  update user
            userService.saveEntityWithValidation(user);

            return new RedirectResult("/allUsers?id=" +  departmentId);

        }catch (AppValidationException e){
            Map<String,String> mapErr = e.getMapErr();
            request.setAttribute("mapErr", mapErr);

            User invalidUser = new User();

            invalidUser.setId(userId);
            invalidUser.setName(nameUser);
            invalidUser.setSurname(surnameUser);
            invalidUser.setEmail(emailUser);
            invalidUser.setAge(ageUser);
            invalidUser.setDepartmentId(departmentId);

            request.setAttribute("allDepartments", departmentService.findAllEntities());
            request.setAttribute("invalidUser", invalidUser);
            request.setAttribute("editUserError", e.getMessage());

            return new ForwardResult("editUser");

        }
    }
}