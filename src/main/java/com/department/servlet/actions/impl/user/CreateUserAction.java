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
import com.department.validation.OvalValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created on 06.04.17.
 */
public class CreateUserAction extends OvalValidator implements Action {

    private UserService userService;
    private DepartmentService departmentService;

    public CreateUserAction(){
        this.userService = new UserServiceImpl();
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
        String nameUser = request.getParameter("userName");
        String surnameUser = request.getParameter("userSurname");
        String emailUser = request.getParameter("userEmail");
        Integer userAge = Integer.parseInt(request.getParameter("userAge"));
        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));

        User user = new User();
        user.setName(nameUser);
        user.setSurname(surnameUser);
        user.setEmail(emailUser);
        user.setAge(userAge);
        user.setCreated(new Date());
        user.setDepartmentId(departmentId);

        try {
        userService.saveEntityWithValidation(user);

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

            request.setAttribute("allDepartments", departmentService.findAllEntities());
            request.setAttribute("invalidUser", invalidUser);

            return new ForwardResult("createUser");

        }
    }
}
