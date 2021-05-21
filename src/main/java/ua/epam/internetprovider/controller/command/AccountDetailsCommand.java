package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.SubscriberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AccountDetailsCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        SubscriberService subscriberService = new SubscriberService();
        HttpSession session = req.getSession();
        Account authorizedAccount = (Account) session.getAttribute("account");
        Subscriber subscriber = subscriberService.getById(authorizedAccount.getId());
        List<Tariff> activeTariffs =  subscriberService.getActiveTariffs(subscriber);
        req.setAttribute("subscriber",subscriber);
        req.setAttribute("activeTariffs",activeTariffs);
        return "/templates/subscriber/account/show.jsp";
    }
}
