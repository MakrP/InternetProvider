package ua.epam.internetprovider.servlet;


import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.service.ServiceService;
import ua.epam.internetprovider.service.TariffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tariffs/*")
public class TariffServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() != null) {
            String[] path = req.getPathInfo().split("/");
            if (path.length == 2) {
                TariffService tariffService = new TariffService();
                ServiceService serviceService = new ServiceService();

                Service service = serviceService.getById(Integer.parseInt(path[1]));
                req.setAttribute("tariffs", tariffService.getServiceTariffs(service));
                req.setAttribute("currentServiceId", service.getId());
                req.getRequestDispatcher("/templates/service/tariffs.jsp").forward(req, resp);
            }
        } else {
                 req.getRequestDispatcher("/templates/service/service.jsp").forward(req,resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
