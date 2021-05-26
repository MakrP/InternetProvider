package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.service.SubscriberService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscriberListCommand implements Command{

    private final SubscriberService subscriberService = ServiceFactory.getSubscriberService();


    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        req.setAttribute("subscribers", subscriberService.getAll());
        return new Forward("/templates/admin/subscriber/list.jsp");
    }
}
