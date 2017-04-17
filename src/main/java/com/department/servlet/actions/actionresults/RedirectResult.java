package com.department.servlet.actions.actionresults;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 06.04.17.
 */
public class RedirectResult extends ForwardResult {
    public RedirectResult(String location) {
        super(location);
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        response.sendRedirect(jsp);
    }
}
