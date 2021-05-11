package ua.epam.internetprovider.servlet;

import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.service.ServiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/service")
public class ServiceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceService serviceService = new ServiceService();
        List<Service> serviceList = serviceService.getAll();
        req.setAttribute("services",serviceList);
        req.getRequestDispatcher("/templates/service/service.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
