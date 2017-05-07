package biz;

/**
 * Created by zihao on 2017/5/6.
 */
public class TokenExpiredException extends AbstractException {
    public TokenExpiredException(String message) {
        super(message);
    }

    @Override
    public int getExceptionCode() {
        return 300;
    }
}
