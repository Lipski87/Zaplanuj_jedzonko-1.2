package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        AdminDao adminDao = new AdminDao();

        List<Admin> adminList = adminDao.findAll();

        boolean adminExist = false;

        for (Admin admin : adminList) {
            if (admin.getEmail().equals(email) && BCrypt.checkpw(password, admin.getPassword())) {
                adminExist = true;
                adminDao.setAdminEnable(admin.getId());
                session.setAttribute("adminEnable", admin.getEnable());
                session.setAttribute("adminId", admin.getId());
            }
        }
        if (adminExist) {
            response.sendRedirect("/contact");

        } else {
            System.out.println("Złe dane");
            doGet(request, response);
        }
    }
}
