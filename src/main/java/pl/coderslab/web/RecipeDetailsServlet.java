package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "RecipeDetailsServlet", value = "/app/recipe/details")
public class RecipeDetailsServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");

    PrintWriter writer = response.getWriter();

    String recipeIdString = request.getParameter("id");

    if (recipeIdString == null) {
      writer.append("BRAK");
    } else {
      try {
        int recipeId = Integer.parseInt(recipeIdString);
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(recipeId);
        request.setAttribute("recipe", recipe);
        //ingredients to Array
        String[]  ingredients = recipe.getIngredients().split(", ");
        request.setAttribute("ingredients", ingredients);

        getServletContext().getRequestDispatcher("/WEB-INF/app-recipe-details.jsp").forward(request, response);
      } catch (NumberFormatException e) {
        writer.append("Podane parametry nie są poprawnym id");
        // todo -> link to main site?
      }
    }
  }
}
