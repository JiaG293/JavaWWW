package vn.edu.iuh.fit.controllers;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.DTO.AccountGrantDTO;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.services.AccountService;
import vn.edu.iuh.fit.utils.JPAUtil;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManagementServlet", value = "/")
public class ManagementServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("Action: " + action);
        if (action != null) {
            switch (action) {
                case "getlistAccountOfRole_admin":
                    List<AccountGrantDTO> accounts = accountService.getListAccountOfRole("admin");
                    System.out.println("sfsdlfk:" + accounts.size() );
                    req.setAttribute("listAccountOfRole", accounts);
                    req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
                    break;
                case "getlistAccountOfRole_user":
                    resp.sendRedirect("/RegisterServlet");
                    break;
                case "logout":
                    resp.sendRedirect("/LogoutServlet");
                    break;
                default:
                    //Khi khong ton tai actino nao thi tra ve 404
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid action");
                    break;
            }
        }
    }

    @Override
    public void destroy() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
