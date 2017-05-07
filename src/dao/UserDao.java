package dao;

import biz.AbstractException;
import biz.InvalidArgumentException;
import biz.SystemException;
import biz.UserAlreadyExistException;
import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zihao on 2017/5/3.
 */
public class UserDao {

    private Connection connection = DBConnection.getConnection();

    public User getUser(String username) {
        String sql = "select * from user where `username` = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(int id) {
        String sql = "select * from user where `id` = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Add a user to database and return its id if succeeded
     * @param user
     * @return
     * @throws AbstractException
     */
    public int addUser(User user) throws AbstractException{
        if (user == null)
            throw  new InvalidArgumentException("User cannot be null");
        String sql = "insert ignore into `user` (`username`, `password`) values(?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new UserAlreadyExistException("User named " + user.getUsername() + " already exist");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SystemException("System exception occurs");
        }
    }

}
