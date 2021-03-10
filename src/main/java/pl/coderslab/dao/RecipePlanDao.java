package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.DayName;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDao {
    private static final String CREATE_RECIPE_PLAN_QUERY = "INSERT INTO recipe_plan(recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?);";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM recipe LEFT JOIN recipe_plan rp on recipe.id = rp.recipe_id WHERE plan_id=? ORDER BY display_order ASC;";
    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM recipe_plan where plan_id = ?;";

    public void create(int planId, String mealName, int displayOrder, int recipeiD, int dayNameId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, recipeiD);
            insertStm.setString(2, mealName);
            insertStm.setInt(3, displayOrder);
            insertStm.setInt(4, dayNameId);
            insertStm.setInt(5, planId);
            int result = insertStm.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<RecipePlan> findById(int planId) {
        List<RecipePlan> DayNameList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY);) {

            statement.setInt(1, planId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RecipePlan RecipePlanAdd = new RecipePlan();
                RecipePlanAdd.setId(resultSet.getInt("id"));
                RecipePlanAdd.setMealName(resultSet.getString("meal_name"));
                RecipePlanAdd.setDisplay_order(resultSet.getInt("display_order"));
                RecipePlanAdd.setDay_name_id(resultSet.getInt("day_name_id"));
                RecipePlanAdd.setPlanId(resultSet.getInt("plan_id"));
                RecipePlanAdd.setRecipeId(resultSet.getInt("recipe_id"));
                RecipePlanAdd.setRecipeDescri(resultSet.getString("description"));
                DayNameList.add(RecipePlanAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DayNameList;
    }

    public void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_QUERY)) {
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

}
