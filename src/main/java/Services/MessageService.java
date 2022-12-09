package Services;

import Dao.MessageDao;
import Entities.Message;

import java.util.List;
import java.util.stream.Collectors;

public class MessageService {
    private MessageDao messageDao;

    public MessageService(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public List<Message> getMessagesBetween(int senderId, int receiverId){
        return messageDao.getAll()
                .stream()
                .filter(m -> m.getSenderId() == senderId && m.getReceiverId() == receiverId)
                .toList();
    }

    public void sendMessage(Message message){
        messageDao.add(message);
    }




}
