package ua.epam.internetprovider.controller;

import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.SubscriberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubscriberService subscriberService = new SubscriberService();
        HttpSession session = req.getSession();
        Account authorizedAccount = (Account) session.getAttribute("account");
        Subscriber subscriber = subscriberService.getById(authorizedAccount.getId());
        List<Tariff> activeTariffs =  subscriberService.getActiveTariffs(subscriber);
        req.setAttribute("subscriber",subscriber);
        req.setAttribute("activeTariffs",activeTariffs);
        req.getRequestDispatcher("/templates/subscriber/account/show.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
