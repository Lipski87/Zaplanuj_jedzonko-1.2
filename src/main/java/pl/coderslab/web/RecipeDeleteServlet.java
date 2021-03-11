
package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecipeDeleteServlet", value = "/app/recipe/delete")
public class RecipeDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer recipeId = Integer.valueOf(request.getParameter("id"));
        Boolean isRecipeInPlan = false;
        RecipeDao recipeDao = new RecipeDao();
        RecipePlanDao recipePlanDao = new RecipePlanDao();

        List<RecipePlan> byRecipeId = recipePlanDao.findByRecipeId(recipeId);
        for (int i = 0; i < byRecipeId.size(); i++) {
            if (byRecipeId.get(i).getRecipeId() == recipeId) {
                isRecipeInPlan = true;
            }
        }
        if (isRecipeInPlan) {
            String msg = "Nie można usunąć przepisu - jest zawarty w planie/planach";
            request.setAttribute("msg", msg);
            request.removeAttribute("id");
            getServletContext()
                    .getRequestDispatcher("/app/recipe/list")
                    .forward(request, response);
        } else {
            //recipeDao.delete(Integer.valueOf(recipeId));
            request.setAttribute("id",recipeId.toString());
            String msg = "Czy chcesz usunąć wskazany przepis? Operacja jest nie odwracalna!";
            request.setAttribute("msg", msg);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/app-delete-confirm.jsp")
                    .forward(request, response);
        }
    }
}
