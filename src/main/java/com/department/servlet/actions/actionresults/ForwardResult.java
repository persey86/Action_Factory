package com.department.servlet.actions.actionresults;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.ServerCloneException;

/**
 * Created on 06.04.17.
 */
public class ForwardResult implements ActionResult{
    protected final String jsp;

    public ForwardResult(String jsp) {
        this.jsp = jsp;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/" + jsp + ".jsp").forward(request,response);
    }
}


