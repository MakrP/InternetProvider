package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand implements Command {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) {
        return new Forward("/templates/error/404.jsp");
    }
}
