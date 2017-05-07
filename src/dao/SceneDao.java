package dao;

import biz.AbstractException;
import biz.InvalidArgumentException;
import biz.SystemException;
import model.Scene;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zihao on 2017/5/6.
 */
public class SceneDao {
    private Connection connection = DBConnection.getConnection();

    public int addScene(Scene scene) throws AbstractException {
        if (scene == null)
            throw new InvalidArgumentException("Scene cannot be null");
        String sql = "insert into `scene` (`url`, `author_id`, `latitude`, `longitude`) values(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, scene.getUrl());
            preparedStatement.setInt(2, scene.getAuthorId());
            preparedStatement.setDouble(3, scene.getLatitude());
            preparedStatement.setDouble(4, scene.getLongitude());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
                return resultSet.getInt(1);
            throw new SystemException("Unknown system error");
        } catch (SQLException e) {
            throw new SystemException("Unknown system error");
        }
    }

    public List<Scene> getSceneList(int offset, int limit) {
        String sql = "SELECT * FROM `scene` ORDER BY `id` DESC limit " + offset + "," + limit;
        ArrayList<Scene> scenes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Scene scene = new Scene();
                scene.setId(resultSet.getInt("id"));
                scene.setAuthorId(resultSet.getInt("author_id"));
                scene.setLatitude(resultSet.getDouble("latitude"));
                scene.setLongitude(resultSet.getDouble("longitude"));
                scene.setUrl(resultSet.getString("url"));
                scenes.add(scene);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scenes;
    }

    public Scene getScene(int id) {
        String sql = "select * from scene where `id` = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            Scene scene = new Scene();
            scene.setId(resultSet.getInt("id"));
            scene.setAuthorId(resultSet.getInt("author_id"));
            scene.setLatitude(resultSet.getDouble("latitude"));
            scene.setLongitude(resultSet.getDouble("longitude"));
            scene.setUrl(resultSet.getString("url"));
            return scene;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
