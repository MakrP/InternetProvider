package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.SubscriberService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AccountDetailsCommand implements Command {

    SubscriberService subscriberService = ServiceFactory.getSubscriberService();

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        Account authorizedAccount = (Account) session.getAttribute("account");
        Subscriber subscriber = subscriberService.getById(authorizedAccount.getId());
        List<Tariff> activeTariffs = subscriberService.getActiveTariffs(subscriber);
        req.setAttribute("subscriber", subscriber);
        req.setAttribute("activeTariffs", activeTariffs);
        return new Forward("/templates/subscriber/account/show.jsp");
    }
}
