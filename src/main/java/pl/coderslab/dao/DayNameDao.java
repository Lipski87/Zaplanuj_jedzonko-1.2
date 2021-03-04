package pl.coderslab.dao;

import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {
    private static final String FIND_ALL_DAY_NAMES_QUERY = "SELECT * FROM day_name ORDER BY display_order ASC;";

    // tworzy listę obiektów DayName z bazy danych, wedłóg kolejności display_order
    public List<DayName> findAll() {
        List<DayName> DayNameList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_DAY_NAMES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DayName DayNameToAdd = new DayName();
                DayNameToAdd.setId(resultSet.getInt("id"));
                DayNameToAdd.setName(resultSet.getString("name"));
                DayNameToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                DayNameList.add(DayNameToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DayNameList;
    }
}
