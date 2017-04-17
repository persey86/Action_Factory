package com.department.servlet.actions.impl.department;

import com.department.exceptions.AppException;
import com.department.services.DepartmentService;
import com.department.services.impl.DepartmentServiceImpl;
import com.department.servlet.actions.Action;
import com.department.servlet.actions.actionresults.ForwardResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 08/04/2017.
 */
public class ShowEditDepartmentPage implements Action {

    private DepartmentService departmentService;

    public ShowEditDepartmentPage(){
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
        request.setAttribute("department",  this.departmentService.findOneEntity(departmentId));
        return new ForwardResult("editDepartment");
    }
}
