package dao;

import biz.AbstractException;
import biz.InvalidArgumentException;
import biz.SystemException;
import model.Comment;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zihao on 2017/5/7.
 */
public class CommentDao {
    private Connection connection = DBConnection.getConnection();

    public int addSceneComment(int commentId, int sceneId) throws AbstractException {
        String sql = "insert into `comment_scene` (`scene_id`, `comment_id`) values(?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, sceneId);
            preparedStatement.setInt(2, commentId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
                return resultSet.getInt(1);
            throw new InvalidArgumentException("Comment or scene does not exist");
        } catch (SQLException e) {
            throw new InvalidArgumentException("Comment or scene does not exist");
        }
    }

    public int addComment(Comment comment) throws  AbstractException {
        if (comment == null)
            throw new InvalidArgumentException("Comment cannot be null");
        String sql = "insert into `comment` (`author_id`, `content`, `post_time`) values(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, comment.getAuthorId());
            preparedStatement.setString(2, comment.getContent());
            preparedStatement.setTimestamp(3, comment.getPostTime());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
                return resultSet.getInt(1);
            throw new SystemException("Unknown system error");
        } catch (SQLException e) {
            throw new SystemException("Unknown system error");
        }
    }
}
