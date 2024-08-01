package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface AdminServiceInterface {

    List<User> findAll();

    User findOne(int id);

    boolean add(User user);

    void setRoleDefault(User user);

    Set<Role> findAllRoles();

    void update(int id, User user);

    void delete(int id);
}