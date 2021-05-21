package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.service.SubscriberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscriberListCommand implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        SubscriberService subscriberService = new SubscriberService();
        req.setAttribute("subscribers", subscriberService.getAll());
        return "/templates/admin/subscriber/list.jsp";
    }
}
