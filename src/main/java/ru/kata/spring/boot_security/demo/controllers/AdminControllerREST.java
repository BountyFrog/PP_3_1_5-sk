package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.AdminService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class AdminControllerREST {

    private final AdminService adminService;

    public AdminControllerREST(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUserList() {
        List<User> userList = adminService.findAll();
        return userList == null && userList.isEmpty() ?
                new ResponseEntity<>(userList, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Set<Role>> getRolesList() {
        Set<Role> roleSet = adminService.findAllRoles();
        return roleSet == null && roleSet.isEmpty() ?
                new ResponseEntity<>(roleSet, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(roleSet, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        final User user = adminService.findOne(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid User user) {
        adminService.add(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<HttpStatus> update(@RequestBody User user) {
        adminService.update(user.getId(), user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        adminService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}