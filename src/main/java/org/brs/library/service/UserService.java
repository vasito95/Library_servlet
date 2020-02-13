package org.brs.library.service;

import org.brs.library.model.dao.UserDao;
import org.brs.library.model.entity.User;

import java.util.Optional;

public class UserService {
     UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public void createUser(User user){
        dao.create(user);
    }
    public void deleteUser(Long id){
        this.dao.delete(id);
    }

    public Optional<User> findUserByEmail(String email){
       return this.dao.findByName(email);
    }

    public Optional<User> findUserByEmailAndPassword(String email, String pass){
        return this.dao.findByEmailAndPassword(email,pass);
    }

}
