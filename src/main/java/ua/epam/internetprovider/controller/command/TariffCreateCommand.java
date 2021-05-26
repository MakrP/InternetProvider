package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.TariffService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TariffCreateCommand implements Command {

    TariffService tariffService = ServiceFactory.getTariffService();

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        if (req.getMethod().equals("GET")) {
            return new Forward("/templates/admin/tariff/create.jsp");
        } else {
            String title = req.getParameter("title");
            String parameterPrice = req.getParameter("price");
            String parameterServiceId = req.getParameter("service");
            int price = Integer.parseInt(parameterPrice);
            long serviceId = Integer.parseInt(parameterServiceId);
            Tariff tariff = new Tariff(title, price, serviceId);
            tariffService.save(tariff);
            return new Forward("/controller?command=AdminTariffList", true);
        }
    }
}
