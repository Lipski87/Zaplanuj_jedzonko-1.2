package pl.coderslab.dao;

import pl.coderslab.utils.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RecipePlanDao {
    private static final String CREATE_RECIPE_PLAN_QUERY = "INSERT INTO recipe_plan(recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?,?,?);";

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
}
