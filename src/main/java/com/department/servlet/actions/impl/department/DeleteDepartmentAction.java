package com.department.servlet.actions.impl.department;

import com.department.exceptions.AppException;
import com.department.services.DepartmentService;
import com.department.services.impl.DepartmentServiceImpl;
import com.department.servlet.actions.Action;
import com.department.servlet.actions.actionresults.ForwardResult;
import com.department.servlet.actions.actionresults.RedirectResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 03.04.17.
 */
public class DeleteDepartmentAction implements Action {

    private DepartmentService departmentService;

    public DeleteDepartmentAction(){
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        String idDeletedDepartment = request.getParameter("departmentId");
        Integer intId = Integer.parseInt(idDeletedDepartment);
        this.departmentService.deleteEntityWithValidation(intId);

        return new RedirectResult("/");
    }
}
