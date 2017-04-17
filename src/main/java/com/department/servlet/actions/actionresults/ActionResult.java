package com.department.servlet.actions.actionresults;

import com.department.exceptions.DepartmentRepositoryException;
import com.department.exceptions.UserRepositoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.rmi.server.ServerCloneException;

/**
 * Created on 06.04.17.
 */
public interface ActionResult {
    void execute (HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException;
}
