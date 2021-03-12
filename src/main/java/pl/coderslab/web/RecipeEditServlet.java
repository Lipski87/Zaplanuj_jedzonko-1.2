package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecipeEditServlet", value = "/app/recipe/edit")
public class RecipeEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));

        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        Integer prep_time = Integer.valueOf(request.getParameter("prep_time"));
        String prep = request.getParameter("prep");
        String ingr = request.getParameter("ingr");

        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(id);

        recipe.setName(name);
        recipe.setDescription(desc);
        recipe.setPreparation_time(prep_time);
        recipe.setPreparation(prep);
        recipe.setIngredients(ingr);

        recipeDao.update(recipe);

        response.sendRedirect("list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(id);

        request.setAttribute("name",recipe.getName());
        request.setAttribute("desc",recipe.getDescription());
        request.setAttribute("prep_time",recipe.getPreparation_time());
        request.setAttribute("prep",recipe.getPreparation());
        request.setAttribute("ingr",recipe.getIngredients());
        request.setAttribute("id",request.getParameter("id"));

        getServletContext().getRequestDispatcher("/WEB-INF/app-recipe-edit.jsp").forward(request,response);


    }
}
