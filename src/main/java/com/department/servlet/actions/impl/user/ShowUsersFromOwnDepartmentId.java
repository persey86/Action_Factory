package com.department.servlet.actions.impl.user;

import com.department.exceptions.UserRepositoryException;
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
import java.util.List;

/**
 * Created on 07.04.17.
 */
public class ShowUsersFromOwnDepartmentId implements Action {

    private UserService userService;
    private DepartmentService departmentService;

    public ShowUsersFromOwnDepartmentId(){
        this.userService = new UserServiceImpl();
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws UserRepositoryException {

        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
        Integer departmentSelectedIdInt = Integer.parseInt(request.getParameter("departmentId"));
        userRepositoryImpl.getUsersWhereDepartmentId(departmentSelectedIdInt);

        List<User> users = userRepositoryImpl.getUsersWhereDepartmentId(departmentSelectedIdInt);

        request.setAttribute("users", users);

        return new RedirectResult("/usersBySelectedDepartment");
    }
}
