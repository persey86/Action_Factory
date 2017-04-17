package com.department.servlet.actions.impl;

import com.department.servlet.actions.Action;
import com.department.servlet.actions.actionresults.ForwardResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotFound implements Action {
    @Override
    public ForwardResult execute(HttpServletRequest request, HttpServletResponse response) {
        return new ForwardResult("404");
    }
}
