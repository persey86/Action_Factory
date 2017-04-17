package com.department.servlet.actions;

import com.department.exceptions.AppException;
import com.department.exceptions.AppValidationException;
import com.department.exceptions.DepartmentRepositoryException;
import com.department.exceptions.UserRepositoryException;
import com.department.servlet.actions.actionresults.ForwardResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

/**
 * Created on 02.04.2017.
 */
public interface Action {
    ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws AppException;
}
