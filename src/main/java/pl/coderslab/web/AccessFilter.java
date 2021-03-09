package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AccessFilter", urlPatterns = "/app/*")
public class AccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();

        if(session.getAttribute("adminEnable").equals(0)){
            ((HttpServletResponse) response).sendRedirect("/login");
            System.out.println("Brak autoryzacji użytkownika");
        } else {
            chain.doFilter(request, response);
            System.out.println("Filtr dostępowy działa!");
        }
    }
    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
