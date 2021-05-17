package ua.epam.internetprovider.controller;

import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.service.SubscriberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/balance")
public class BalanceServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/subscriber/account/increase_balance.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int total = Integer.parseInt(req.getParameter("increase_total"));
        SubscriberService subscriberService = new SubscriberService();
        Account account = (Account) req.getSession().getAttribute("account");
        Subscriber subscriber = subscriberService.getById(account.getId());
        subscriberService.increaseBalance(subscriber, total);
        resp.sendRedirect(getServletContext().getContextPath() + "/account");
    }
}
