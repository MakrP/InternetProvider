package ua.epam.internetprovider.controller.command;

import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.entity.Account;
import ua.epam.internetprovider.entity.Subscriber;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.SubscriberService;
import ua.epam.internetprovider.service.TariffService;
import ua.epam.internetprovider.service.exception.AlreadyHasTariffException;
import ua.epam.internetprovider.service.exception.HasTariffOfSameServiceException;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.exception.SubscriberAmountNotEnoughException;
import ua.epam.internetprovider.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetTariffCommand implements Command {

    private final TariffService tariffService = ServiceFactory.getTariffService();
    private final SubscriberService subscriberService = ServiceFactory.getSubscriberService();

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        Account authorizedAccount = ((Account) req.getSession().getAttribute("account"));
        Subscriber subscriber = subscriberService.getById(authorizedAccount.getId());
        long tariffId = Long.parseLong(req.getParameter("tariffId"));
        Tariff wantedTariff = tariffService.getById(tariffId);
        boolean confirmation = Boolean.parseBoolean(req.getParameter("confirmation"));
        Forward forward = new Forward("/controller?command=TariffList&serviceId=" + wantedTariff.getServiceId());
        try {
            subscriberService.subscribeOnTariff(subscriber, wantedTariff, confirmation);
            forward.setResource(forward.getResource() + "&toastMessage=" + "Now you can use " + wantedTariff.getTitle());
            forward.setResource(forward.getResource() + "&success=true");
            forward.setRedirect(true);
        } catch (AlreadyHasTariffException e) {
            req.setAttribute("toastMessage", "You already have" + wantedTariff.getTitle() + "tariff");
            req.setAttribute("success", false);
        } catch (HasTariffOfSameServiceException e) {
            req.setAttribute("wantedTariff", wantedTariff);
            req.setAttribute("modalMessage", "You already have " + e.getService().getTitle() + " tariff " + e.getHasTariff().getTitle());
            req.setAttribute("success", false);
        } catch (SubscriberAmountNotEnoughException e) {
            req.setAttribute("toastMessage", "You dong have enough total to get " + wantedTariff.getTitle() + " tariff");
            req.setAttribute("success", false);
        }
        return forward;
    }
}
