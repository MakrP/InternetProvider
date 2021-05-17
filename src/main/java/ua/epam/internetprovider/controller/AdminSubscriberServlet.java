package ua.epam.internetprovider.controller;

import ua.epam.internetprovider.service.SubscriberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/subscriber")
public class AdminSubscriberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubscriberService subscriberService = new SubscriberService();
        req.setAttribute("subscribers", subscriberService.getAll());
        req.getRequestDispatcher("/templates/admin/subscriber/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
