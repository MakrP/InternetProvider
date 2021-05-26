package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

public class LanguageCommand implements Command {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter("lang");
        String previousUrl = req.getParameter("prevUrl");
        Config.set(req.getSession(),Config.FMT_LOCALE,language);
        return new Forward(previousUrl);
    }
}
