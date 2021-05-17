package ua.epam.internetprovider.controller;


import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/login/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getParameter("login");
        String password = (String) req.getParameter("password");
        AccountService service = new AccountService();
        Account account = service.getAccountByTokens(login, password);
        if (account == null) {
            req.setAttribute("message", "Bad tokens");
            doGet(req, resp);
        }
        HttpSession session = req.getSession();
        session.setAttribute("account", account);
        String contextPath = getServletContext().getContextPath();
        if (account.getRole().equals("SUBSCRIBER")) {
            List<Service> services = (List<Service>) getServletContext().getAttribute("services");
            long firstServiceId = services.get(0).getId();
            resp.sendRedirect(contextPath + "/tariffs/" + firstServiceId);
        } else {
            resp.sendRedirect(contextPath + "/admin");
        }
    }
}
