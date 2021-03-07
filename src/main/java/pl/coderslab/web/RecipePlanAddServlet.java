package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecipePlanAddServlet", value = "/app/recipe/plan/add")
public class RecipePlanAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("plan"));
        String mealName = request.getParameter("name");
        int displayOrder = Integer.parseInt(request.getParameter("number"));
        int recipeiD = Integer.parseInt(request.getParameter("recipie"));
        int dayNameId = Integer.parseInt(request.getParameter("day"));

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.create(planId, mealName, displayOrder, recipeiD, dayNameId);

        response.sendRedirect("/app/recipe/plan/add");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        List<Plan> planList = planDao.findAll();
        request.setAttribute("planList", planList);

        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipeList = recipeDao.findAll();
        request.setAttribute("recipeList", recipeList);

        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> dayNameList = dayNameDao.findAll();
        request.setAttribute("dayNameList", dayNameList);

        getServletContext().getRequestDispatcher("/WEB-INF/app-schedules-meal-recipe.jsp").forward(request, response);
    }
}
