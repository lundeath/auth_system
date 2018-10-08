package com.global.ukrainets.controller;

import com.global.ukrainets.utils.Scrambler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * @author yurii.ukrainets
 */
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        String userDepartment = request.getParameter("department");

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try { //managing connection to database
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/msql", "root", "root");
            Class.forName("com.mysql.jdbc.Driver");
            statement = connection.createStatement();
            String query = "select * from users";
            resultSet = statement.executeQuery(query);

            boolean isValid = false;

            while (resultSet.next()) { //compare login,password and department with database
                if (userLogin.equals(resultSet.getString("login"))
                        && userPassword.equals(resultSet.getString("password"))
                        && userDepartment.equals(resultSet.getString("department_id"))) {
                    isValid = true;
                    break;
                }
            }

            if (isValid) { //checking correctness of validation and redirecting to proper department
                String depURL = "/WEB-INF/pages/departments/dep_0" + userDepartment + ".jsp"; //creating url to proper department

                request.getRequestDispatcher(depURL).forward(request, response);
            } else {
                request.setAttribute("message", "Incorrect login or password");
                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally { //closing connections
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
