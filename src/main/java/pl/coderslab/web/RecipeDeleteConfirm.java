package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecipeDeleteConfirm", value = "/app/recipe/delete/confirm")
public class RecipeDeleteConfirm extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RecipeDao recipeDao = new RecipeDao();
    Integer recipeId = Integer.valueOf(request.getParameter("id"));

    recipeDao.delete(Integer.valueOf(recipeId));

    getServletContext().getRequestDispatcher("/app/recipe/list").forward(request, response);
  }
}
