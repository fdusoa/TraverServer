package biz;

/**
 * Created by zihao on 2017/5/6.
 */
public class InvalidArgumentException extends AbstractException {
    public InvalidArgumentException(String message) {
        super(message);
    }

    @Override
    public int getExceptionCode() {
        return 200;
    }
}
