package com.department.servlet.actions.impl.department;

import com.department.servlet.actions.Action;
import com.department.servlet.actions.actionresults.ForwardResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 03.04.17.
 */
public class ShowCreateDepartmentPageAction implements Action {
    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) {
        return new ForwardResult("createDepartment");
    }
}
