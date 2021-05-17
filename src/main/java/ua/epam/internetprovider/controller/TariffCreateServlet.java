package ua.epam.internetprovider.controller;

import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.TariffService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tariff/create")
public class TariffCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/admin/tariff/create.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String parameterPrice = req.getParameter("price");
        String parameterServiceId = req.getParameter("service");
        int price = Integer.parseInt(parameterPrice);
        long serviceId = Integer.parseInt(parameterServiceId);
        Tariff tariff = new Tariff(title,price,serviceId);
        TariffService tariffService = new TariffService();
        tariffService.save(tariff);
        String contextPath = req.getServletContext().getContextPath();
        resp.sendRedirect(contextPath + "/admin/tariff");
    }
}
