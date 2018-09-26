package com.allenanker.servlet;

import com.allenanker.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InitServlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        List<User> users = new ArrayList<>();
        this.getServletContext().setAttribute("users", users);
    }
}
