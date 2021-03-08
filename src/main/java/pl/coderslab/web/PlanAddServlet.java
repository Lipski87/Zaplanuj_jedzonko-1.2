package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "PlanAddServlet", value = "/app/plan/add")
public class PlanAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planName = request.getParameter("name");
        String planDescription = request.getParameter("description");
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        // na razie dodaje admin_id "1", do zmiany na admin id z sesji
        Plan plan = new Plan();
        plan.setName(planName);
        plan.setDescription(planDescription);
        plan.setCreated(timeStamp);
        plan.setAdminId(1);

        PlanDao planDao = new PlanDao();
        planDao.create(plan);
        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/app-add-schedules.jsp").forward(request, response);
    }
}
