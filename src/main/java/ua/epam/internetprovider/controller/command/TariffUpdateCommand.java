package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.TariffService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TariffUpdateCommand implements Command {
    private final TariffService tariffService = ServiceFactory.getTariffService();
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        if(req.getMethod().equals("GET")) {
            long tariffId = Long.parseLong(req.getParameter("tariffId"));
            Tariff tariff = tariffService.getById(tariffId);
            req.setAttribute("tariff",tariff);
            return new Forward("/templates/admin/tariff/update.jsp");
        } else {
            long tariffId = Long.parseLong(req.getParameter("tariffId"));
            String title = req.getParameter("title");
            String priceParam = req.getParameter("price");
            int price = Integer.parseInt(priceParam);
            Tariff tariff = new Tariff(tariffId,title,price);
            tariffService.update(tariff);
            return new Forward( "/controller?command=AdminTariffList");
        }
    }
}
