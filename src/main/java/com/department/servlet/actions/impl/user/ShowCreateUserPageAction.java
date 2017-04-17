package com.department.servlet.actions.impl.user;

import com.department.exceptions.DepartmentRepositoryException;
import com.department.repository.impl.DepartmentRepositoryImpl;
import com.department.servlet.actions.Action;
import com.department.servlet.actions.actionresults.ForwardResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 06.04.17.
 */
public class ShowCreateUserPageAction implements Action {
    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws DepartmentRepositoryException {

        DepartmentRepositoryImpl departmentRepositoryImpl = new DepartmentRepositoryImpl();
        request.setAttribute("allDepartments", departmentRepositoryImpl.findAll());

        return new ForwardResult("createUser");
    }
}
