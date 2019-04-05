package com.contacts.restapi.domain;

import javax.persistence.*;

@Entity
@Table
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                ", firstName='" + firstName + '\'' +
                ", secondName=" + secondName + '\'' +
                ", email=" + email + '\'' +
                ", phoneNumber=" + phoneNumber + '\'' +
                '}';
    }
}
