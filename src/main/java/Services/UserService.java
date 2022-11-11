package Services;

import Dao.UserDao;
import Entities.User;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser(User user){
        userDao.add(user);
    }

    public int getUserId(User user){
        return userDao.getAl()
                .stream()
                .filter(u -> u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElse(null)
                .getId();
    }
}
