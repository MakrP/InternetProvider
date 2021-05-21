package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.AccountService;
import ua.epam.internetprovider.service.SubscriberService;
import ua.epam.internetprovider.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TariffCreateCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getMethod().equals("GET")) {
            return "/templates/admin/tariff/create.jsp";
        } else {
            String title = req.getParameter("title");
            String parameterPrice = req.getParameter("price");
            String parameterServiceId = req.getParameter("service");
            int price = Integer.parseInt(parameterPrice);
            long serviceId = Integer.parseInt(parameterServiceId);
            Tariff tariff = new Tariff(title,price,serviceId);
            TariffService tariffService = new TariffService();
            tariffService.save(tariff);
            return "/controller?command=AdminTariffList";
        }
    }
}
