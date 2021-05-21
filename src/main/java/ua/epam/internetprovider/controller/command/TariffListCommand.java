package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.ServiceService;
import ua.epam.internetprovider.service.TariffService;
import ua.epam.internetprovider.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TariffListCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        ServiceService serviceService = new ServiceService();
        TariffService tariffService = new TariffService();
        String serviceId = req.getParameter("serviceId");
        Service service = serviceService.getById(Integer.parseInt(serviceId));
        String currentPageParameter = req.getParameter("page");
        String orderField = req.getParameter("orderField");
        String order = req.getParameter("order");
        int currentPage = 1;
        if (currentPageParameter != null) {
            currentPage = Integer.parseInt(currentPageParameter);
        }
        int numberOfElements = 3;
        int totalRecords = tariffService.getTariffsCountOfService(service);
        int pagesCount = totalRecords % numberOfElements == 0 ?
                (totalRecords / numberOfElements)
                :
                (totalRecords / numberOfElements + 1);
        List<Tariff> tariffList = tariffService.getServiceTariffs(service, (currentPage - 1) * numberOfElements, numberOfElements,orderField,order);
        req.setAttribute("page", currentPage);
        req.setAttribute("totalPages", pagesCount);
        req.setAttribute("tariffs", tariffList);
        req.setAttribute("serviceId", service.getId());
        req.setAttribute("orderField",orderField);
        req.setAttribute("order",order);
        return "/templates/subscriber/tariff/list.jsp";
    }
}
