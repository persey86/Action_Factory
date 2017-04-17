package com.department.servlet.actions;

import com.department.servlet.actions.impl.*;
import com.department.servlet.actions.impl.department.*;
import com.department.servlet.actions.impl.user.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 02.04.2017.
 */
public class ActionFactory {
    public static Map<String, Action> actions = new HashMap<>();

              static   {
                  actions.put("GET/", new ShowAllDepartmentsAction());
                  actions.put("GET/allUsers", new ShowAllUsersAction());
                  actions.put("GET/createDepartment", new ShowCreateDepartmentPageAction());
                  actions.put("GET/createUser", new ShowCreateUserPageAction());

                  actions.put("POST/createUser", new CreateUserAction());
                  actions.put("POST/createDepartment", new CreateDepartmentAction());
                  actions.put("POST/deleteDepartment", new DeleteDepartmentAction());
                  actions.put("POST/deleteUser", new DeleteUserAction());
                  actions.put("POST/selectedDepartmentId", new ShowUsersFromOwnDepartmentId());
                  actions.put("GET/notFound", new NotFound());
                  actions.put("GET/editDepartment", new ShowEditDepartmentPage());

                  actions.put("POST/editDepartment", new EditDepartmentAction());

                  actions.put("GET/editUser", new ShowEditUserPage());
                  actions.put("POST/editUser", new EditUserAction());
              }

    public static Action getAction(HttpServletRequest request) {
        String uri = request.getMethod() + request.getRequestURI();
        if (actions.containsKey(uri)) {
            return actions.get(uri);
        } else {
            return actions.get("GET/notFound");
        }
    }
}