package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getMethod().equals("GET")) {
            return "/templates/login/login.jsp";
        } else if (req.getMethod().equals("POST")) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            AccountService service = new AccountService();
            Account account = service.getAccountByTokens(login, password);
            if (account == null) {
                req.setAttribute("errorMessage", "Bad tokens");
                return "/templates/login/login.jsp";
            }
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            if (account.getRole().equals("SUBSCRIBER")) {
                List<Service> services = (List<Service>) req.getServletContext().getAttribute("services");
                long firstServiceId = services.get(0).getId();
                return "/controller?command=TariffList&serviceId=" + firstServiceId + "&page=" + 1;
            } else {
                return "/templates/admin/index.jsp";
            }
        }
        return "/error";
    }
}
