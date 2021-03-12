package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Contact", value = "/contact")
public class Contact extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession httpSession = request.getSession();
            int enable = (int) httpSession.getAttribute("adminEnable");
            httpSession.setAttribute("isEnable",enable);
            getServletContext().getRequestDispatcher("/WEB-INF/contact.jsp").forward(request,response);
        } catch (NullPointerException e) {
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/WEB-INF/contact.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
