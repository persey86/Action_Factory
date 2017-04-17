package com.department.servlet;

import com.department.servlet.actions.Action;
import com.department.servlet.actions.ActionFactory;
import com.department.servlet.actions.actionresults.ActionResult;
import com.department.servlet.actions.actionresults.ForwardResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 02.04.2017.
 */
public class MainServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getAction(request);
            ActionResult view = action.execute(request, response);

            view.execute(request,response);

        } catch (com.department.exceptions.AppException e) {
            ForwardResult error = new ForwardResult("errorPage");
            request.setAttribute("error", e.getMessage());
            error.execute(request, response);
            e.printStackTrace();
        }
    }
}
