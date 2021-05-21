package ua.epam.internetprovider.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

public class LanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter("lang");
        String previousUrl = req.getParameter("prevUrl");
        Config.set(req.getSession(),Config.FMT_LOCALE,language);
        return previousUrl;
    }
}
