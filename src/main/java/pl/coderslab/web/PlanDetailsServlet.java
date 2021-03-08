package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlanDetailsServlet", value = "/app/plan/details/")
public class PlanDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        int planId = Integer.parseInt(request.getParameter("id"));
        Plan plan = planDao.read(planId);
        request.setAttribute("plan", plan);

        DayNameDao nameDao = new DayNameDao();
        List<DayName> dayNameList = nameDao.findDays(planId);
        request.setAttribute("dayNameList", dayNameList);

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        List<RecipePlan> recipePlanList = recipePlanDao.findById(planId);
        request.setAttribute("recipePlanList", recipePlanList);

        getServletContext().getRequestDispatcher("/WEB-INF/app-details-schedules.jsp").forward(request, response);
    }
}
