package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return new Forward("/controller?command=Login");
    }
}
