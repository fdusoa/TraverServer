package biz;

import context.UserContext;
import dao.SceneDao;
import model.Scene;
import model.User;

import java.util.List;

/**
 * Created by zihao on 2017/5/6.
 */
public class SceneBiz {
    private SceneDao sceneDao = new SceneDao();

    public List<Scene> getSceneList(int offset, int limit) {
        return sceneDao.getSceneList(offset, limit);
    }

    public Scene getScene(int id) {
        return sceneDao.getScene(id);
    }

    /**
     * Add a scene to database. Return its id if succeeded. Throw AuthorizationException while user has not logged
     * in or the user tried to add a scene with others' author id
     * @param scene scene added
     * @return id of added scene
     * @throws AuthorizationException
     */
    public int addScene(Scene scene) throws AbstractException {
        User user = UserContext.getCurrentUser();
        if (user == null || user.getId() != scene.getAuthorId()) {
            throw new AuthorizationException("Permission denied");
        }
        return sceneDao.addScene(scene);
    }
}
