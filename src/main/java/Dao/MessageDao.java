package Dao;

import Entities.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao implements DAO<Message>{

    private Connection connection;

    public MessageDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Message message) {
        String query = "insert into \"messages\" (sender_id, receiver_id, msg_content) values (?, ?, ?)";
        try{
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, message.getSenderId());
            st.setInt(2, message.getReceiverId());
            st.setString(3, message.getContent());
            st.execute();
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remove(int id) {
        String query = "delete from \"messages\" where id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message get(int id) {
        Message msg = null;
        String query = "select * from \"messages\" where id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                msg = new Message(rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("receiver_id"),
                        rs.getString("msg_content"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return msg;
    }

    @Override
    public List<Message> getAll() {
        List<Message> messages = new ArrayList<>();
        String query = "select * from \"messages\"";

        try{
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                messages.add(new Message(rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("receiver_id"),
                        rs.getString("msg_content")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return messages;
    }
}
