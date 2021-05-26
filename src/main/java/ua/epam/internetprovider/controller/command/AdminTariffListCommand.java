package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.service.TariffService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminTariffListCommand implements Command {
    private final TariffService tariffService = ServiceFactory.getTariffService();

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        req.setAttribute("tariffs",tariffService.getAll());
        return new Forward("/templates/admin/tariff/list.jsp");
    }
}
