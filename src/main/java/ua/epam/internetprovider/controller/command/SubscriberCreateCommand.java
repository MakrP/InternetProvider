package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.service.AccountService;
import ua.epam.internetprovider.service.SubscriberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscriberCreateCommand implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getMethod().equals("GET")) {
            return "/templates/admin/subscriber/create.jsp";
        } else {
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
            return "/controller?command=SubscriberListCommand";
        }
    }
}
