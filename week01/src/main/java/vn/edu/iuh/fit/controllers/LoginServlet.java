package vn.edu.iuh.fit.controllers;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.DTO.AccountGrantDTO;
import vn.edu.iuh.fit.entities.DTO.Response;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.services.AccountService;
import vn.edu.iuh.fit.utils.JPAUtil;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "LoginController", value = "/login")
public class LoginServlet extends HttpServlet {
    private AccountService accountService;
    private EntityManager em;

    @Override
    public void init() throws ServletException {
        em = JPAUtil.getEntityManager();
        AccountRepository accountRepository = new AccountRepository(em);
        LogRepository logRepository = new LogRepository(em);
        accountService = new AccountService(accountRepository, logRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        Response<AccountGrantDTO> auth = accountService.loginAuthentication(username, password, session.getId());

        //Hop le: code 200 se tra set attributes session
        //Khong hop le: code 404 se chuyen ve trang login kem theo ma loi
        if (auth.getStatusCode() == 200 ) {
            if(auth.getData().getRole().getRoleId().equals("admin")){
                session.setAttribute("accountData", auth.getData());
                resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
            } else {
                session.setAttribute("accountData", auth.getData());
                resp.sendRedirect(req.getContextPath() + "/home.jsp");
            }

        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=" + auth.getMessage());
        }
    }

    //Dong ket noi db
    @Override
    public void destroy() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}

