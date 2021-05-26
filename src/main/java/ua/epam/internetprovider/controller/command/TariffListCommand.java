package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.ServiceService;
import ua.epam.internetprovider.service.TariffService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;
import ua.epam.internetprovider.util.PaginationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TariffListCommand implements Command {
    private final TariffService tariffService = ServiceFactory.getTariffService();
    private final ServiceService serviceService = ServiceFactory.getServiceService();

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String serviceId = req.getParameter("serviceId");
        Service service = serviceService.getById(Integer.parseInt(serviceId));
        String currentPageParameter = req.getParameter("page");
        String orderField = req.getParameter("orderField");
        String order = req.getParameter("order");
        String toastMessage = req.getParameter("toastMessage");
        // TODO add filter to add this params to req
        int currentPage = 1;
        if (currentPageParameter != null) {
            currentPage = Integer.parseInt(currentPageParameter);
        }
        int totalRecords = tariffService.getTariffsCountOfService(service);
        int pagesCount = PaginationUtil.getPagesCount(totalRecords, PaginationUtil.SUBSCRIBER_TARIFFS_PER_PAGE);
        List<Tariff> tariffList = tariffService.getServiceTariffs(service,
                (currentPage - 1) * PaginationUtil.SUBSCRIBER_TARIFFS_PER_PAGE,
                PaginationUtil.SUBSCRIBER_TARIFFS_PER_PAGE, orderField, order);
        req.setAttribute("page", currentPage);
        req.setAttribute("totalPages", pagesCount);
        req.setAttribute("tariffs", tariffList);
        req.setAttribute("serviceId", service.getId());
        req.setAttribute("orderField", orderField);
        req.setAttribute("order", order);
        return new Forward("/templates/subscriber/tariff/list.jsp");
    }
}
