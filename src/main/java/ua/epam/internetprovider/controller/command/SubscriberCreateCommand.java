package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Role;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.service.AccountService;
import ua.epam.internetprovider.service.SubscriberService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscriberCreateCommand implements Command{

    private final AccountService accountService = ServiceFactory.getAccountService();
    private final SubscriberService subscriberService = ServiceFactory.getSubscriberService();

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        if(req.getMethod().equals("GET")) {
            return new Forward("/templates/admin/subscriber/create.jsp");
        } else {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            Account account =  new Account(login,password, Role.SUBSCRIBER);
            accountService.saveAccount(account);
            Subscriber subscriber = new Subscriber(account.getId(),name,surname);
            subscriberService.save(subscriber);
            return new Forward("/controller?command=SubscriberListCommand", true);
        }
    }
}
