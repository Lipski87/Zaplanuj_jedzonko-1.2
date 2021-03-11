package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.LastAddedPlanDetails;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Dashboard", value = "/app/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        int adminId = (int) httpSession.getAttribute("adminId");

        RecipeDao recipeDao = new RecipeDao();
        request.setAttribute("recipesNumber", recipeDao.findNumberOfRecipesByUser(adminId));

        PlanDao planDao = new PlanDao();
        request.setAttribute("plansNumber", planDao.findNumberOfPlansByUser(adminId));

            ArrayList<LastAddedPlanDetails> plan = planDao.findLastPlanAdded(adminId);

            if (plan.isEmpty()) {
                String msg = "Użytkownik nie dodał jeszcze żadnego planu";
                request.setAttribute("planNameMsg", msg);
            } else {
                String lastPlan = plan.get(0).toString();
                request.setAttribute("planName", lastPlan);
            }

        getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
