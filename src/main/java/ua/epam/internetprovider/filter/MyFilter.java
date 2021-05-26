package ua.epam.internetprovider.filter;

import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.service.ServiceService;
import ua.epam.internetprovider.service.exception.ServiceException;
import ua.epam.internetprovider.service.factory.ServiceFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter("/controller/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        if (httpRequest.getServletContext().getAttribute("services") == null) {
            ServiceService serviceService = ServiceFactory.getServiceService();
            List<Service> serviceList = null;
            try {
                serviceList = serviceService.getAll();
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            httpRequest.getServletContext().setAttribute("services", serviceList);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
