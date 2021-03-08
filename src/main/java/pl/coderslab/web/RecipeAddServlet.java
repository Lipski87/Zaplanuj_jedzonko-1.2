package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "RecipeAddServlet", value = "/app/recipe/add")
public class RecipeAddServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (request.getParameter("name").isEmpty()
        || request.getParameter("description").isEmpty()
        || request.getParameter("preparation_time").isEmpty()
        || request.getParameter("preparation").isEmpty()
        || request.getParameter("ingredients").isEmpty()) {
      String msg = "Not all data inputed";
      request.setAttribute("msg", msg);
      getServletContext()
          .getRequestDispatcher("/WEB-INF/app-recipe-add.jsp")
          .forward(request, response);
    } else {
      String name = request.getParameter("name");
      String description = request.getParameter("description");
      int preparationTime = Integer.parseInt(request.getParameter("preparation_time"));
      // Todo add validation of preparation time
      String preparation = request.getParameter("preparation");
      String ingredients = request.getParameter("ingredients");
      RecipeDao recipeDao = new RecipeDao();
      Recipe recipe =
          new Recipe(
              name,
              ingredients,
              description,
              LocalDateTime.now(),
              LocalDateTime.now(),
              preparationTime,
              preparation,
              1);
      // Todo AdminID take from cookie? or session? set as 1 for now
      recipeDao.create(recipe);

      response.sendRedirect("list");
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    getServletContext()
        .getRequestDispatcher("/WEB-INF/app-recipe-add.jsp")
        .forward(request, response);
  }
}
