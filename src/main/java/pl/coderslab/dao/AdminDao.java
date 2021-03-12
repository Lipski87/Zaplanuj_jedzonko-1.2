package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name,last_name, email, password, superadmin, enable) VALUES (?,?,?,?,0,0);";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ?, last_name = ?, email = ? WHERE id = ?;";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String SET_ADMIN_ENABLE_QUERY = "UPDATE admins set enable = 1 where admins.id = ?;";
    private static final String SET_ADMIN_DISABLE_QUERY = "UPDATE admins set enable = 0 where admins.id = ?;";

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
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }

            return admin;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Create admin exception");
            return null;
        }
    }

    public Admin read(int adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ADMIN_QUERY)) {
            preparedStatement.setInt(1, adminId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(hashPassword(resultSet.getString("password")));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Read admin exception");
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
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Update admin exception");
        }
    }

    public void delete(int adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {

            preparedStatement.setInt(1, adminId);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Delete admin exception");
        }
    }

    public List<Admin> findAll () {
        List<Admin> adminsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperadmin(resultSet.getInt("superadmin"));
                admin.setEnable(resultSet.getInt("enable"));
                adminsList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Find all admins exception");
        }
        return adminsList;
    }

    public void setAdminEnable (int adminId) {
        try (Connection connection = DbUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SET_ADMIN_ENABLE_QUERY);
            preparedStatement.setInt(1,adminId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Set admin enable exception");
        }
    }

    public void setAdminDisable (int adminId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SET_ADMIN_DISABLE_QUERY);
            preparedStatement.setInt(1, adminId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Set admin disable exception");
        }
    }
}
