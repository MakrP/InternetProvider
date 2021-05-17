package ua.epam.internetprovider.controller;

import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.service.AccountService;
import ua.epam.internetprovider.service.SubscriberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subscriber/create")
public class CreateSubscriberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/admin/subscriber/create.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AccountService accountService = new AccountService();
        Account account =  new Account(login,password,"SUBSCRIBER");
        accountService.saveAccount(account);
        Subscriber subscriber = new Subscriber(account.getId(),name,surname);
        SubscriberService subscriberService = new SubscriberService();
        subscriberService.save(subscriber);
        String contextPath = req.getServletContext().getContextPath();
        resp.sendRedirect(contextPath + "/admin/subscriber");
    }
}
