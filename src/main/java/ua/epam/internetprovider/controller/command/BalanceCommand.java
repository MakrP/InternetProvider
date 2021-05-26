package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.service.SubscriberService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BalanceCommand implements Command {
    SubscriberService subscriberService = ServiceFactory.getSubscriberService();

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        if(req.getMethod().equals("GET")) {
            return new Forward("/templates/subscriber/account/increase_balance.jsp");
        } else {
            int total = Integer.parseInt(req.getParameter("increase_total"));
            Account account = (Account) req.getSession().getAttribute("account");
            Subscriber subscriber = subscriberService.getById(account.getId());
            subscriberService.increaseBalance(subscriber, total);
            return new Forward("/controller?command=AccountDetails",true);
        }
    }
}
