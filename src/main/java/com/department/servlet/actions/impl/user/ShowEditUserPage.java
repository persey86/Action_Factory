package com.department.servlet.actions.impl.user;

import com.department.exceptions.DepartmentRepositoryException;
import com.department.exceptions.UserRepositoryException;
import com.department.repository.impl.DepartmentRepositoryImpl;
import com.department.repository.impl.UserRepositoryImpl;
import com.department.servlet.actions.Action;
import com.department.servlet.actions.actionresults.ForwardResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowEditUserPage implements Action {
    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws DepartmentRepositoryException, UserRepositoryException {

        Integer userId = Integer.parseInt(request.getParameter("userId"));

        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl();
        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();

        request.setAttribute("allDepartments", departmentRepositoryImpl.findAll());

        request.setAttribute("user", userRepositoryImpl.findOne(userId));

        return new ForwardResult("editUser");
    }
}
