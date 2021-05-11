package ua.epam.internetprovider.filter;

import ua.epam.internetprovider.entity.Service;
import ua.epam.internetprovider.service.ServiceService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getServletContext().getAttribute("services") == null) {
            ServiceService serviceService = new ServiceService();
            List<Service> serviceList = serviceService.getAll();
            request.getServletContext().setAttribute("services",serviceList);
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
