package Entities;

import lombok.Data;

@Data
public class Like {
    private int id;
    private int likerId;
    private int likedId;
}
