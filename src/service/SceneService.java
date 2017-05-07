package service;

import biz.AbstractException;
import biz.SceneBiz;
import biz.UserBiz;
import context.UserContext;
import model.Scene;
import model.User;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

import java.util.List;

/**
 * Created by zihao on 2017/5/1.
 */
public class SceneService {
    private UserBiz userBiz = new UserBiz();
    private SceneBiz sceneBiz = new SceneBiz();

    public OMElement uploadScene(String token, Scene scene) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://service", "uploadScene");
        OMElement response = fac.createOMElement("uploadSceneResponse", omNs);
        OMElement status = fac.createOMElement("status", omNs);

        try {
            User user = userBiz.getAuthenticatedUser(token);
            try (UserContext userContext = new UserContext(user)) {
                int sceneId = sceneBiz.addScene(scene);
                status.addChild(fac.createOMText(status, "1"));

                OMElement id = fac.createOMElement("id", omNs);
                id.addChild(fac.createOMText(id, "" + sceneId));

                response.addChild(status);
                response.addChild(id);
            }
        } catch (AbstractException e) {
            status.addChild(fac.createOMText(status, "0"));
            OMElement exception = e.toOMElement(omNs);
            response.addChild(status);
            response.addChild(exception);
        }
        return response;
    }

    public Scene[] getScenePage(int offset, int limit) {
        List<Scene> scenes = sceneBiz.getSceneList(offset, limit);
        return scenes.toArray(new Scene[scenes.size()]);
    }

    public Scene getScene(int id) {
        return sceneBiz.getScene(id);
    }

    public Scene commentToScene(String token, String comment, int sceneId) {
        return null;
    }

    public Scene great(String token) {
        return null;
    }
}
