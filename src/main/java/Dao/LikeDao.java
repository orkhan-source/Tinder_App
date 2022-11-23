package Dao;

import Entities.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDao implements DAO<Like>{

    private Connection connection;

    public LikeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Like like) {
        String query = "insert into \"likes\" (liker_id, liked_id) values (?, ?)";
        try{
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, like.getLikerId());
            st.setInt(2, like.getLikedId());
            st.execute();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remove(int id) {
        String query = "delete from \"likes\" where id = ?";

        try{
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Like get(int id) {
        Like like = null;
        String query = "select id, liker_id, liked_id from \"likes\" where id = ?";

        try{
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                like = new Like(rs.getInt("id"), rs.getInt("liker_id"), rs.getInt("liked_id"));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return like;
    }

    @Override
    public List<Like> getAll() {
        List<Like> likes = new ArrayList<>();
        String query = "select * from \"likes\" ";

        try{
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                likes.add(new Like(rs.getInt("id"), rs.getInt("liker_id"), rs.getInt("liked_id")));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return likes;
    }
}
