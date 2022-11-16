package Services;

import Dao.UserDao;
import Entities.User;

import java.util.List;
import java.util.stream.Collectors;

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

    public User getUser(int id){
        return userDao.get(id);
    }

    public List<User> getAllUsers(){
        return userDao.getAl();
    }

    public List<User> getAllUsersExcept(int id){
        return userDao.getAl().stream().filter(u -> u.getId() != id).collect(Collectors.toList());
    }


}
