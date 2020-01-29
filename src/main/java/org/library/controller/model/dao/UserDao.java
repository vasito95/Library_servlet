package org.library.controller.model.dao;

import org.library.controller.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByName(String name);
    Optional<User> findByEmailAndPassword(String email, String pass);
}
