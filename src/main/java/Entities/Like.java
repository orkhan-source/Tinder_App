package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Like {
    private int id;
    private int likerId;
    private int likedId;

    public Like(int likerId, int likedId) {
        this.likerId = likerId;
        this.likedId = likedId;
    }

    public int getLikerId() {
        return likerId;
    }

    public int getLikedId() {
        return likedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return likerId == like.likerId && likedId == like.likedId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(likerId, likedId);
    }
}
