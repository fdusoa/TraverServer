package service;

import biz.AbstractException;
import biz.UserBiz;
import model.User;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

/**
 * Created by zihao on 2017/5/3.
 */
public class UserService {
    private UserBiz userBiz = new UserBiz();

    public OMElement register(String username, String password) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://service", "register");
        OMElement response = fac.createOMElement("registerResponse", omNs);
        OMElement status = fac.createOMElement("status", omNs);

        try {
            int id = userBiz.register(username, password);
            status.addChild(fac.createOMText(status, "1"));

            OMElement uid = fac.createOMElement("id", omNs);
            uid.addChild(fac.createOMText(uid, "" + id));

            response.addChild(status);
            response.addChild(uid);

        } catch (AbstractException e) {
            status.addChild(fac.createOMText(status, "1"));
            response.addChild(status);
            response.addChild(e.toOMElement(omNs));
        }
        return response;
    }

    public User echo(User user) {
        return user;
    }

    public OMElement login(String username, String password) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://service", "login");
        OMElement response = fac.createOMElement("loginResponse", omNs);
        OMElement status = fac.createOMElement("status", omNs);

        try {
            User user = userBiz.getAuthenticatedUser(username, password);
            status.addChild(fac.createOMText(status, "1"));
            OMElement token = fac.createOMElement("token", omNs);
            token.addChild(fac.createOMText(token, userBiz.generateToken(user)));

            OMElement id = fac.createOMElement("id", omNs);
            id.addChild(fac.createOMText(id, "" + user.getId()));

            response.addChild(status);
            response.addChild(id);
            response.addChild(token);
        } catch (AbstractException e) {
            status.addChild(fac.createOMText(status, "0"));
            OMElement exception = e.toOMElement(omNs);
            response.addChild(status);
            response.addChild(exception);
        }
        return response;
    }

}
