package ua.epam.internetprovider.controller;

import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.TariffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/tariff")
public class AdminTariff extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffService tariffService = new TariffService();
        req.setAttribute("tariffs",tariffService.getAll());
        req.getRequestDispatcher("/templates/admin/tariff/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req,resp);
    }
}
