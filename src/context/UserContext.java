package context;

import model.User;

/**
 * Created by zihao on 2017/5/3.
 */
public class UserContext implements AutoCloseable {
    private static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public UserContext(User user) {
        currentUser.set(user);
    }

    public static User getCurrentUser() {
        return currentUser.get();
    }

    @Override
    public void close() {
        currentUser.remove();
    }
}
