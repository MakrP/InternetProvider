package ua.epam.internetprovider.controller.command;

import com.itextpdf.text.DocumentException;
import ua.epam.internetprovider.controller.Forward;
import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.entity.Tariff;
import ua.epam.internetprovider.service.ServiceService;
import ua.epam.internetprovider.service.TariffService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;
import ua.epam.internetprovider.util.TariffPDFGenerator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGenerateCommand implements Command {
    private final TariffService tariffService = ServiceFactory.getTariffService();
    private final ServiceService serviceService = ServiceFactory.getServiceService();

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        int tariffId = Integer.parseInt(req.getParameter("tariffId"));
        Tariff tariff = tariffService.getById(tariffId);
        Service service = tariffService.getTariffService(tariff);
        try {
            resp.setContentType("application/pdf;charset=UTF-8");
            resp.addHeader("Content-Disposition", "inline; filename=" + tariff.getTitle() + ".pdf");
            ServletOutputStream out = resp.getOutputStream();
            ByteArrayOutputStream baos = TariffPDFGenerator.generateTariffPDF(tariff,service);
            baos.writeTo(out);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
