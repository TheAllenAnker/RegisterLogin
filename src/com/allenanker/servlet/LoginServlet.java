package com.allenanker.servlet;

import com.allenanker.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<User> users = (List<User>) request.getServletContext().getAttribute("users");
        for (User user : users) {
            if (username.equals(user.getAccountName()) && password.equals(user.getPassword())) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/success.jsp");
                return;
            }
        }
        request.setAttribute("msg", "Login failed. Account name or password is incorrect.");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
