package com.department.servlet.actions.impl.user;

import com.department.exceptions.UserRepositoryException;
import com.department.repository.impl.UserRepositoryImpl;
import com.department.servlet.actions.Action;
import com.department.servlet.actions.actionresults.ForwardResult;
import com.department.servlet.actions.actionresults.RedirectResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 06.04.17.
 */
public class DeleteUserAction implements Action {
    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) throws UserRepositoryException {

            UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
            String idDeletingUser = request.getParameter("userId");
            Integer intId = Integer.parseInt(idDeletingUser);
            userRepositoryImpl.delete(intId);

        return new RedirectResult("/");
    }
}

