package ua.epam.internetprovider.controller;

import ua.epam.internetprovider.controller.command.Command;
import ua.epam.internetprovider.controller.command.factory.CommandFactory;

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
        Forward forward = null;
        Command command;
        try {
            command = CommandFactory.getCommand(req);
            forward = command.execute(req, resp);
        } catch (Exception e) {
            Logger.getLogger("TmpLogger").log(Level.SEVERE, "Error during command " + e);
        }
        if (forward != null) {
            if (forward.isRedirect()) {
                String contextPath = req.getServletContext().getContextPath();
                resp.sendRedirect(contextPath + forward.getResource());
            } else {
                req.getRequestDispatcher(forward.getResource()).forward(req, resp);

            }
        }
    }
}
