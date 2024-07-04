package ru.kata.spring.boot_security.demo.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    @Size(min = 2, max = 100, message = "Firstname should be between 2 and 30 characters")
    @NotEmpty(message = "Firstname should not be empty")
    private String username;

    @Column(name = "lastname")
    @Size(min = 2, max = 100, message = "Lastname should be between 2 and 30 characters")
    @NotEmpty(message = "Lastname should not be empty")
    private String lastname;

    @Column(name = "age")
    @Min(value = 0, message = "Age should be greater than 0")
    @NotNull
    private int age;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    private String password;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    public User() {
    }

    public User(String username, String lastname, int age, String email, String password, List<Role> roles) {
        this.username = username;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @Size(min = 2, max = 100, message = "Firstname should be between 2 and 30 characters") @NotEmpty(message = "Firstname should not be empty") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 2, max = 100, message = "Firstname should be between 2 and 30 characters") @NotEmpty(message = "Firstname should not be empty") String username) {
        this.username = username;
    }

    public @Size(min = 2, max = 100, message = "Lastname should be between 2 and 30 characters") @NotEmpty(message = "Lastname should not be empty") String getLastname() {
        return lastname;
    }

    public void setLastname(@Size(min = 2, max = 100, message = "Lastname should be between 2 and 30 characters") @NotEmpty(message = "Lastname should not be empty") String lastname) {
        this.lastname = lastname;
    }

    @Min(value = 0, message = "Age should be greater than 0")
    @NotNull
    public int getAge() {
        return age;
    }

    public void setAge(@Min(value = 0, message = "Age should be greater than 0") @NotNull int age) {
        this.age = age;
    }

    public @Email @NotEmpty(message = "Email should not be empty") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotEmpty(message = "Email should not be empty") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Password should not be empty") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password should not be empty") String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(username, user.username) && Objects.equals(lastname, user.lastname) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, lastname, age, email, password, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}