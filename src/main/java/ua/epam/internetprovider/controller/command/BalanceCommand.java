package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.service.SubscriberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BalanceCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getMethod().equals("GET")) {
            return "/templates/subscriber/account/increase_balance.jsp";
        } else {
            int total = Integer.parseInt(req.getParameter("increase_total"));
            SubscriberService subscriberService = new SubscriberService();
            Account account = (Account) req.getSession().getAttribute("account");
            Subscriber subscriber = subscriberService.getById(account.getId());
            subscriberService.increaseBalance(subscriber, total);
            return "/controller?command=AccountDetails";
        }
    }
}
