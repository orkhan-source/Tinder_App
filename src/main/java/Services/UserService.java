package Services;

import Dao.LikeDao;
import Dao.UserDao;
import Entities.Like;
import Entities.User;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private UserDao userDao;

    private LikeService likeService;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService(Connection connection, UserDao userDao) {
        this.userDao = userDao;
        likeService = new LikeService(new LikeDao(connection));
    }

    public void addUser(User user){
        userDao.add(user);
    }

    public int getUserId(User user){
        return userDao.getAll()
                .stream()
                .filter(u -> u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElse(null)
                .getId();
    }

    public User getUser(int id){
        return userDao.get(id);
    }

    public List<User> getAllUsers(int id){
        return userDao.getAll().stream().filter(u -> u.getId() != id).collect(Collectors.toList());
    }

    public List<User> getLikedUsers(int id, List<Like> likes){
        return likes.stream().map(l -> getUser(l.getLikedId())).collect(Collectors.toList());
    }




}
