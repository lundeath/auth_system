package com.global.ukrainets.controller;


import com.global.ukrainets.utils.Scrambler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yurii.ukrainets
 */
public class DepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String decodedToken = Scrambler.base64decode(request.getParameter("depToken")); //getting department ID
        String depURL = "/WEB-INF/pages/departments/dep_0" + decodedToken + ".jsp"; //creating url to proper department

        request.getRequestDispatcher(depURL).forward(request, response);
    }

}
