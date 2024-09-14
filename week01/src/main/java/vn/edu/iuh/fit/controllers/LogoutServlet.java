package vn.edu.iuh.fit.controllers;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.LogRepository;
import vn.edu.iuh.fit.services.AccountService;
import vn.edu.iuh.fit.utils.JPAUtil;

import java.io.IOException;

@WebServlet(name = "LogoutController", value = "/logout")
public class LogoutServlet extends HttpServlet {
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
        String sessionId = req.getSession().getId();
        System.out.println("check session" + sessionId);

        boolean rs = accountService.logout(sessionId);

        HttpSession session = req.getSession();
        session.removeAttribute("accountData");

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setValue(null);
                    cookie.setPath(req.getContextPath());
                    cookie.setMaxAge(0); // Xóa cookie
                    resp.addCookie(cookie);
                }
            }
        }

        if (rs) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
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
