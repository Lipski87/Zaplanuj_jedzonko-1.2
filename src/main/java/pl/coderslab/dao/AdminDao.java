package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.utils.BCrypt;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(firstName, lastName, email, password, superadmin, enable) VALUES (?,?,?,?,0,1)";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET firstName = ?, lastName = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, hashPassword(admin.getPassword()));

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create user exception");
        }

        return null;
    }

    public Admin read(int adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ADMIN_QUERY)) {
            preparedStatement.setInt(1, adminId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("firstName"));
                    admin.setLastName(resultSet.getString("lastName"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(hashPassword(resultSet.getString("password")));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Read user exception");
        }
        return admin;
    }

    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            preparedStatement.setInt(4, admin.getId());
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getEmail());
            preparedStatement.setString(4, this.hashPassword(admin.getPassword()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Update user exception");
        }
    }

    public void delete(int adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {

            preparedStatement.setInt(1, adminId);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Delete user exception");
        }
    }

}
