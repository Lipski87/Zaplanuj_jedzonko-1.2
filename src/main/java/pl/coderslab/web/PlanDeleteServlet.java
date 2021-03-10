package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PlanDeleteServlet", value = "/app/plan/delete")
public class PlanDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        int planId = Integer.parseInt(request.getParameter("planId"));
        recipePlanDao.delete(planId);
        planDao.delete(planId);

        response.sendRedirect("/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();

        if (request.getParameter("id") != null){
            int planId = Integer.parseInt(request.getParameter("id"));
            Plan plan = planDao.read(planId);
            request.setAttribute("plan", plan);
            getServletContext().getRequestDispatcher("/WEB-INF/app-delete-schedules.jsp").forward(request, response);
        } else {
            response.sendRedirect("/app/plan/list");
        }

    }
}
