package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminIndexCommand implements Command {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) {
        return new Forward("templates/admin/index.jsp");
    }
}
