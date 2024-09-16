package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.Account;

import java.io.IOException;


//Controller chinh xu li cac su kien
@WebServlet(name = "ControlServlet", value = "")
public class ControlServlet extends HttpServlet {


    //Chuyen huong den cac controller
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        //Neu action dung se chuyen sang cac controller de xu li
        if (action != null) {
            switch (action) {
                case "login":
                    response.sendRedirect("/login.jsp");
                    break;
                case "register":
                    response.sendRedirect("/RegisterServlet");
                    break;
                case "logout":
                    response.sendRedirect("/LogoutServlet");
                    break;
                default:
                    //Khi khong ton tai actino nao thi tra ve 404
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid action");
                    break;
            }
        } else {
            // Khi khong co tham so se chuyen den trang login

            //Thieu xac thuc sessions khi dang nhap
            if (action == null) {
                if (request.getSession(false) == null || request.getSession().getAttribute("accountData") == null) {
                    response.sendRedirect("login.jsp");
                } else {
                    response.sendRedirect("dashboard.jsp");
                }
            }
        }
    }


    //Chuyen huong phuong thuc post den get
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

