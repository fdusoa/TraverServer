package biz;

/**
 * Created by zihao on 2017/5/6.
 */
public class AuthorizationException extends AbstractException {

    public AuthorizationException(String message) {
        super(message);
    }

    @Override
    public int getExceptionCode() {
        return 100;
    }

}
