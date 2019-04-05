package com.contacts.restapi.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "userlist")
public class User {
    @Id
    private String login;
    private String password;
    private LocalDateTime lastvisit;

    public User() {
    }

    public User(String login, String password, LocalDateTime lastvisit) {
        this.login = login;
        this.password = password;
        this.lastvisit = lastvisit;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastvisit() {
        return lastvisit;
    }

    public void setLastvisit(LocalDateTime lastvisit) {
        this.lastvisit = lastvisit;
    }
}
