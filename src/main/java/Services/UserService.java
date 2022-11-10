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
}
