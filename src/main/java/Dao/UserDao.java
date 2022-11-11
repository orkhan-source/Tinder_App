package Dao;

import DB.DBConnection;
import Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements DAO<User>{

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(User user) {

        String query = "insert into \"users\" (email, u_password, u_name, surname, gender, imgurl) values (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, user.getEmail());
            st.setString(2, user.getPassword());
            st.setString(3, user.getName());
            st.setString(4, user.getSurname());
            st.setBoolean(5, user.getGender());
            st.setString(6, user.getImageUrl());
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        String query = "delete from \"users\" where id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User get(int id) {
        User user = null;
        String query = "select id, email, u_name, surname, gender, imgurl from \"users\" where id = ?";

        try{
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                user = new User(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getString("imgurl"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public List<User> getAl() {
        List<User> users = new ArrayList<>();
        String query = "select * from \"users\"";

        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                boolean gender = rs.getBoolean("gender");
                String imageUrl = rs.getString("imgurl");
                users.add(new User(id, email, password, name, surname, gender, imageUrl));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Problem occurred", e);
        }

        return users;
    }

}
