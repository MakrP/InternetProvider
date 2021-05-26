package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Role;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.service.AccountService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginCommand implements Command {
    private final AccountService service = ServiceFactory.getAccountService();
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        if (req.getMethod().equals("GET")) {
            return new Forward("/templates/login/login.jsp");
        } else {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            Account account = service.getAccountByTokens(login, password);
            if (account == null) {
                req.setAttribute("errorMessage", "Bad tokens");
                return new Forward("/templates/login/login.jsp");
            }
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            if (account.getRole().equals(Role.SUBSCRIBER)) {
                List<Service> services = (List<Service>) req.getServletContext().getAttribute("services");
                long firstServiceId = services.get(0).getId();
                return new Forward("/controller?command=TariffList&serviceId=" + firstServiceId + "&page=" + 1, true);
            } else {
                return new Forward("/templates/admin/index.jsp", true);
            }
        }
    }
}
