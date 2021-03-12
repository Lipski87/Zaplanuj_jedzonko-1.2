package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet(name = "PlanEditServlet", value = "/app/plan/edit")
public class PlanEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int planId = Integer.parseInt(request.getParameter("planId"));
        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        int adminId = (Integer) session.getAttribute("adminId");

        Plan editedPlan = new Plan(planId, planName, planDescription, timeStamp, adminId);
        PlanDao planDao = new PlanDao();
        planDao.update(editedPlan);

        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        int planId = Integer.parseInt(request.getParameter("id"));
        Plan plan = planDao.read(planId);
        request.setAttribute("plan", plan);

        getServletContext().getRequestDispatcher("/WEB-INF/app-edit-schedules.jsp").forward(request, response);
    }
}
