package biz;

import dao.CommentDao;
import dao.SceneDao;
import model.Comment;
import model.Scene;

import java.util.List;

/**
 * Created by zihao on 2017/5/7.
 */
public class CommentBiz {
    private CommentDao commentDao = new CommentDao();
    private SceneDao sceneDao = new SceneDao();

    public List<Comment> getSceneComments(int sceneId) {
        return null;
    }

    public void commentToScene(Scene scene, Comment comment) {

    }
}
