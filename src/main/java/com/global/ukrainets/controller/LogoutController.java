package com.global.ukrainets.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        Cookie sessionID = null;
        HttpSession session = req.getSession(false);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) sessionID = cookie;
            }
        }
        if(sessionID !=null) {
            sessionID.setMaxAge(0);
        }
        if(session != null){
            session.invalidate();
        }

        req.getRequestDispatcher("/login").forward(req, resp);
    }
}
