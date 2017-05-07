package biz;

import dao.UserDao;
import model.User;
import util.StringUtil;

/**
 * Created by zihao on 2017/5/5.
 */
public class UserBiz {
    private UserDao userDao = new UserDao();

    public User getAuthenticatedUser(String token) throws AbstractException {
        String[] parts = token.split(":");
        if (parts.length != 3) {
            return null;
        }
        try {
            int id = Integer.parseInt(parts[0]);
            long expire = Long.parseLong(parts[1]);
            if (expire < System.currentTimeMillis()) {
                throw new TokenExpiredException("Token has expired.");
            }
            String hash = parts[2];
            if (hash.equals(generateAuthHash(id, expire)))
                return userDao.getUser(id);
        } catch (NumberFormatException ignored) {}
        throw new AuthenticationException("Invalid token");
    }

    public User getAuthenticatedUser(String username, String password) throws AuthenticationException {
        User user = userDao.getUser(username);
        if (user != null && user.getPassword().equals(StringUtil.getMD5(password))) {
            return user;
        } else {
            throw new AuthenticationException("Username and password don't match");
        }
    }

    public String generateAuthHash(int id, long expire) {
        String hash = id + ":" + expire + ":" + "it's a secret";
        return StringUtil.getMD5(hash);
    }

    public String generateToken(User user) {
        long current = System.currentTimeMillis();
        long duration = 7 * 24 * 60 * 60 * 1000;
        long expire = current + duration;
        return "" + user.getId() + ":" + expire + ":" + generateAuthHash(user.getId(), expire);
    }

    public int register(String username, String password) throws AbstractException {
        if (username == null || password == null)
            throw new InvalidArgumentException("Username or password cannot be null");
        User user = new User();
        user.setUsername(username);
        user.setPassword(StringUtil.getMD5(password));
        return userDao.addUser(user);
    }
}
