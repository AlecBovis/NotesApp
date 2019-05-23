package com.abovis.notesapp.repositories;

import com.abovis.notesapp.models.User;
import com.orm.SugarRecord;

import java.util.List;

public class UserRepository {

    public static void create(String username, String fullname, String email, String password) {

        User user = new User();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);

        SugarRecord.save(user);
    }

    public static User login(String username, String password){

        List<User> users = SugarRecord.find(User.class,
                "username=? and password=?", username, password);

        if(!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }

    public static User load(Long id){
        User user = SugarRecord.findById(User.class, id);
        return user;
    }
}
