package vn.edu.iuh.fit.week01.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.week01.db.MariaDbConnection;
import vn.edu.iuh.fit.week01.models.User;
import vn.edu.iuh.fit.week01.services.AccountService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "Login", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username").trim().toLowerCase();
        String password = req.getParameter("password").trim().toLowerCase();
        System.out.println(username + " " + password);
        try {
            if (AccountService.loginUser(username, password)) {
                out.println("<h1>Login successful!</h1>");
            } else {
                out.println("<h1>Login failed!</h1>");
            }
        } catch (SQLException e) {
            out.println("<h1>Login failed from server!</h1>");
            throw new RuntimeException(e);

        }

    }
}
