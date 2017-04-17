package com.department.servlet.actions.impl.department;

import com.department.exceptions.AppException;
import com.department.exceptions.AppValidationException;
import com.department.models.Department;
import com.department.services.DepartmentService;
import com.department.services.impl.DepartmentServiceImpl;
import com.department.servlet.actions.Action;
import com.department.servlet.actions.actionresults.ForwardResult;
import com.department.servlet.actions.actionresults.RedirectResult;
import com.department.validation.OvalValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created on 03.04.17.
 */
public class CreateDepartmentAction extends OvalValidator implements Action {

    private DepartmentService departmentService;

    public CreateDepartmentAction(){
        this.departmentService = new DepartmentServiceImpl();
    }

    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        String name = request.getParameter("departmentName");
        Department department = new Department();
        department.setName(name);
        department.setCreated(new Date());


        try {
            departmentService.saveEntityWithValidation(department);
            return new RedirectResult("/");

        } catch (AppValidationException e) {
            Map<String,String> mapErr = e.getMapErr();
            request.setAttribute("mapErr", mapErr);

            // give old dep name to index page
            request.setAttribute("departmentName", name);

            // give all departs to index page
            request.setAttribute("allDepartments", departmentService.findAllEntities());

            return new ForwardResult("index");
        }
    }
}