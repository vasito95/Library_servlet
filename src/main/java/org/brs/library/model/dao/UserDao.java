package org.brs.library.model.dao;

import org.brs.library.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByName(String name);
    Optional<User> findByEmailAndPassword(String email, String pass);
}
