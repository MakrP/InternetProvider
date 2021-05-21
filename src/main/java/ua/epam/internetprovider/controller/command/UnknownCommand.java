package ua.epam.internetprovider.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/error.jsp";
    }
}
