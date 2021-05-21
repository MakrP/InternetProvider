package ua.epam.internetprovider.controller;

import ua.epam.internetprovider.controller.command.Command;
import ua.epam.internetprovider.controller.command.factory.CommandFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/controller/*")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/error.jsp";
        Command command = null;
        try {
            command = CommandFactory.getCommand(req);
            url = command.execute(req, resp);
        } catch (Exception e) {
            Logger.getLogger("TmpLogger").log(Level.SEVERE, "Error during command " + e);
        }
        if (req.getMethod().equals("GET")) {
            req.getRequestDispatcher(url).forward(req, resp);
        } else {
            String contextPath = getServletContext().getContextPath();
            resp.sendRedirect(contextPath + url);
        }
    }
}
