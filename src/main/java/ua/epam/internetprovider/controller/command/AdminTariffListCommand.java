package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.service.TariffService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminTariffListCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        TariffService tariffService = new TariffService();
        req.setAttribute("tariffs",tariffService.getAll());
        return "/templates/admin/tariff/list.jsp";
    }
}
