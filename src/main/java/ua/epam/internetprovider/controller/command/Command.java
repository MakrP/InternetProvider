package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException;
}
