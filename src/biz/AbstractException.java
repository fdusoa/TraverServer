package biz;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

/**
 * Created by zihao on 2017/5/6.
 */
public abstract class AbstractException extends Exception {
    public AbstractException(String message) {
        super(message);
    }

    public String getExceptionName() {
        return this.getClass().getName();
    }

    public abstract int getExceptionCode();

    public OMElement toOMElement(OMNamespace omNamespace) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMElement exception = fac.createOMElement("exception", omNamespace);
        OMElement code = fac.createOMElement("code", omNamespace);
        OMElement message = fac.createOMElement("message", omNamespace);
        OMElement name = fac.createOMElement("name", omNamespace);

        code.addChild(fac.createOMText(code, "" + this.getExceptionCode()));
        message.addChild(fac.createOMText(message, "" + this.getMessage()));
        name.addChild(fac.createOMText(name, "" + this.getExceptionName()));

        exception.addChild(code);
        exception.addChild(name);
        exception.addChild(message);
        return exception;
    }
}
