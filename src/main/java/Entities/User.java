package Entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private boolean gender;
    private String imgurl;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String name, String surname, boolean gender, String imgurl) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.imgurl = imgurl;
    }

    public boolean getGender(){
        return this.gender;
    }






}
