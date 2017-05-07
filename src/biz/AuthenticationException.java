package biz;

/**
 * Created by zihao on 2017/5/6.
 */
public class AuthenticationException extends AbstractException {
    public AuthenticationException(String message) {
        super(message);
    }

    @Override
    public int getExceptionCode() {
        return 400;
    }
}
