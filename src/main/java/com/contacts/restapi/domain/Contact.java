package com.contacts.restapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties( { "user" })
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

    public void SetContact(Contact newContact) {
        this.firstName = newContact.getFirstName();
        this.secondName = newContact.getSecondName();
        this.email = newContact.getEmail();
        this.phoneNumber = newContact.getPhoneNumber();
    }

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
}
