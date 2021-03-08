package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AdminDao adminDao = new AdminDao();
        List<Admin> adminList = adminDao.findAll();
        boolean uniqueEmail = true;

        for (Admin admin : adminList) {
            if (admin.getEmail().equals(email)){
                uniqueEmail = false;
                break;
            }
        }
        if (uniqueEmail) {
            Admin newAdmin = new Admin();
            newAdmin.setFirstName(firstname);
            newAdmin.setLastName(surname);
            newAdmin.setEmail(email);
            newAdmin.setPassword(password);
            adminDao.create(newAdmin);

            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            System.out.println("Ten email został już użyty do rejestracji. Proszę podać inny.");
            doGet(request,response);
        }
    }
}
