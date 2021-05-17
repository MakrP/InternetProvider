package ua.epam.internetprovider.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

@WebServlet("/language")
public class LanguageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("lang");
        String prefUrl = req.getParameter("prevUrl");
        Config.set(req.getSession(),Config.FMT_LOCALE,language);
        resp.sendRedirect(prefUrl);
    }
}
