package ua.epam.internetprovider.controller;


import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.ServiceService;
import ua.epam.internetprovider.service.SubscriberService;
import ua.epam.internetprovider.service.TariffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tariffs/*")
public class TariffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() != null) {
            String[] path = req.getPathInfo().split("/");
            if (path.length == 2) {
                ServiceService serviceService = new ServiceService();
                Service service = serviceService.getById(Integer.parseInt(path[1]));
                TariffService tariffService = new TariffService();
                String currentPageParameter = req.getParameter("currentPage");
                int currentPage = 1;
                if (currentPageParameter != null) {
                    currentPage = Integer.parseInt(currentPageParameter);
                }
                int numberOfElements = 3;
                int totalRecords = tariffService.getTariffsCountOfService(service);
                int pagesCount = totalRecords % numberOfElements == 0 ? (totalRecords / numberOfElements) : (totalRecords / numberOfElements + 1);
                List<Tariff> tariffList = tariffService.getServiceTariffs(service, (currentPage - 1) * numberOfElements, numberOfElements);
                req.setAttribute("currentPage", currentPage);
                req.setAttribute("totalPages", pagesCount);
                req.setAttribute("tariffs", tariffList);
                req.setAttribute("currentServiceId", service.getId());
                req.getRequestDispatcher("/templates/subscriber/tariff/list.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/templates/subscriber/tariff/list.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() != null) {
            String[] path = req.getPathInfo().split("/");
            if (path.length == 2) {
                TariffService tariffService = new TariffService();
                SubscriberService subscriberService = new SubscriberService();
                Tariff tariff = tariffService.getById(Integer.parseInt(path[1]));
                Service service = tariffService.getTariffService(tariff);
                long accountId = ((Account) (req.getSession().getAttribute("account"))).getId();
                Subscriber subscriber = subscriberService.getById(accountId);
                subscriberService.addTariff(subscriber, tariff);
                String contextPath = getServletContext().getContextPath();
                resp.sendRedirect(contextPath + "/tariffs/" + service.getId());
            }
        } else {
            req.getRequestDispatcher("/templates/subscriber/tariff/list.jsp").forward(req, resp);
        }
    }


}
