package com.department.servlet.actions.impl.user;

import com.department.exceptions.UserRepositoryException;
import com.department.models.User;
import com.department.repository.impl.UserRepositoryImpl;
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
