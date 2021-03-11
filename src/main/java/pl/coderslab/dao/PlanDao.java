package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.LastAddedPlanDetails;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    //zapytania SQL
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan ORDER BY created DESC;";
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan(name,description,created,admin_id) VALUES (?,?,?,?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = ?, admin_id = ? WHERE id = ?;";
    private static final String FIND_NUMBER_OF_PLANS_BY_USER_QUERY ="SELECT COUNT(*) AS NUMBER FROM plan WHERE admin_id = ? ;";
  private static final String FIND_LAST_ADDED_DETAILS_PLAN_BY_USER_QUERY =
      "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description, plan.name as plan_name\n"
          + "FROM `recipe_plan` JOIN day_name on day_name.id = day_name_id JOIN recipe on recipe.id = recipe_id Join plan on plan.id = plan_id\n"
          + "WHERE recipe_plan.plan_id = (SELECT MAX(id) from plan WHERE admin_id = ?)\n"
          + "ORDER by day_name.display_order, recipe_plan.display_order;";
    //Get plan by id; pobiera informacje z bazy danych i zwraca stworzony obiekt na podstawie podanego id
    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

    //Return all plans; zwraca listę obiektów wszystkich planów, posortowane od najnowszego do najstarszego
    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    //Create plan; tworzy plan w bazie danych, zwraca obiekt z wygenerowanym id
    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setString(3, plan.getCreated());
            insertStm.setInt(4, plan.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Remove plan by id; usuwa plan z bazy danych na podstawie podanego id
    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Update plan; robi update do bazy danych
    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setInt(5, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.setInt(4, plan.getAdminId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  public int findNumberOfPlansByUser(int admin_id) {
    int numberOfRecipes = -1;
    try (Connection connection = DbUtil.getConnection();
        PreparedStatement statement =
            connection.prepareStatement(FIND_NUMBER_OF_PLANS_BY_USER_QUERY)) {
      statement.setInt(1, admin_id);
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          numberOfRecipes = resultSet.getInt("NUMBER");
        }
      }
      return numberOfRecipes;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return numberOfRecipes;
        }

    public ArrayList<LastAddedPlanDetails> findLastPlanAdded (int adminId){
        ArrayList<LastAddedPlanDetails> arrayList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_LAST_ADDED_DETAILS_PLAN_BY_USER_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                int start = 0;
                while (resultSet.next()) {
                    LastAddedPlanDetails lastAddedPlanDetails = new LastAddedPlanDetails();
                    lastAddedPlanDetails.setDayName(resultSet.getString("day_name"));
                    lastAddedPlanDetails.setMealName(resultSet.getString("meal_name"));
                    lastAddedPlanDetails.setRecipeName(resultSet.getString("recipe_name"));
                    lastAddedPlanDetails.setRecipeDescription(resultSet.getString("recipe_description"));
                    lastAddedPlanDetails.setPlanName(resultSet.getString("plan_name"));
                    arrayList.add(lastAddedPlanDetails);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

}
