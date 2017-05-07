package biz;

/**
 * Created by zihao on 2017/5/6.
 */
public class UserAlreadyExistException extends AbstractException {
    public UserAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public int getExceptionCode() {
        return 600;
    }
}
