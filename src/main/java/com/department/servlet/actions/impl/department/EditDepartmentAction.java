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
 * Created on 08/04/2017.
 */
public class EditDepartmentAction extends OvalValidator implements Action {


    private DepartmentService departmentService;

    public EditDepartmentAction(){
        this.departmentService = new DepartmentServiceImpl();
    }


    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String name = request.getParameter("departmentName");

        Department department = new Department();
        department.setName(name);
        department.setCreated(new Date());


        try {
            // saving and redirect to main page
            departmentService.saveEntityWithValidation(department);

            return new RedirectResult("/");
        } catch (AppValidationException e){


            Map<String,String> mapErr = e.getMapErr();

            // give old dep name

            Department departmentFromSecetedId = departmentService.findOneEntity(departmentId);
            departmentFromSecetedId.setName(name);
            request.setAttribute("department", departmentFromSecetedId);

            // show error message on index page
            request.setAttribute("mapErr", mapErr);

            return new ForwardResult("editDepartment");
        }
    }
}
