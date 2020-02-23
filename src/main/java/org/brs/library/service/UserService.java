package org.brs.library.service;

import org.brs.library.exception.UserNotFoundException;
import org.brs.library.model.dao.UserDao;
import org.brs.library.model.entity.User;

public class UserService {
     UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public void createUser(User user){
        dao.create(user);
    }

    public User findUserByEmailAndPassword(String email, String pass) throws UserNotFoundException {
        return this.dao.findByEmailAndPassword(email,pass).orElseThrow(UserNotFoundException::new);
    }

}
