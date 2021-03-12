package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Edit-user", value = "/app/edit-user")
public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        int adminId = (int) httpSession.getAttribute("adminId");
        AdminDao adminDao = new AdminDao();
        request.setAttribute("admin", adminDao.read(adminId));

        getServletContext().getRequestDispatcher("/WEB-INF/edit-user.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");

        HttpSession httpSession = request.getSession();
        int adminId = (int) httpSession.getAttribute("adminId");

        if (!firstname.equals("") && !lastname.equals("") && !email.equals("")){
            Admin admin = new Admin();
            admin.setFirstName(firstname);
            admin.setLastName(lastname);
            admin.setEmail(email);
            admin.setId(adminId);

            AdminDao adminDao = new AdminDao();
            adminDao.update(admin);

        }

        response.sendRedirect("/app/edit-user");
    }
}