package Services;

import Dao.LikeDao;
import Entities.Like;

import java.util.List;
import java.util.stream.Collectors;

public class LikeService {

    private LikeDao likeDao;

    public LikeService(LikeDao likeDao) {
        this.likeDao = likeDao;
    }

    public void addLike(Like like){
        if(!isLikeExist(like)){
            likeDao.add(like);
        }
    }
    public void removeLike(int id){
        likeDao.remove(id);
    }
    public Like getLike(int id){
        return likeDao.get(id);
    }

    public List<Like> getAllLikes(){
        return likeDao.getAll();
    }

    public boolean isLikeExist(Like like){
        return getAllLikes().stream()
                        .anyMatch(l -> (l.equals(like)));
    }

    public int getLikeId(Like like){
        return getAllLikes().stream()
                .filter(l -> l.getLikerId() == like.getLikerId() && l.getLikedId() == like.getLikedId())
                .findFirst()
                .orElse(null)
                .getId();
    }

    public List<Like> getLikesFromUser(int id){
        return getAllLikes().stream().filter(l -> l.getLikerId() == id).collect(Collectors.toList());
    }
}
