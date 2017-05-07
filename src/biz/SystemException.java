package biz;

/**
 * Created by zihao on 2017/5/6.
 */
public class SystemException extends AbstractException {
    public SystemException(String message) {
        super(message);
    }

    @Override
    public int getExceptionCode() {
        return 500;
    }
}
