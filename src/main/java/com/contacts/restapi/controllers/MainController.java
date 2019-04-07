package com.contacts.restapi.controllers;

import com.contacts.restapi.Repository.ContactRepo;
import com.contacts.restapi.Repository.UserRepo;
import com.contacts.restapi.domain.Contact;
import com.contacts.restapi.domain.User;
import com.contacts.restapi.exception.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    private final ContactRepo contactRepo;
    private final UserRepo userRepo;

    @Autowired
    public MainController(ContactRepo contactRepo, UserRepo userRepo) {
        this.contactRepo = contactRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/contacts")
    public List<Contact> getContacts(@RequestParam(value="login") String login,
                                     @RequestParam(value="password") String password) {
        User user = getUser(login);

        if(user.getPassword().equals(password)) {
            return contactRepo.findByUserLike(user);
        }
        else {
            throw new ForbiddenException();
        }
    }

    @GetMapping("/contacts/{id}")
    public Contact getContact(@PathVariable Long id,
                              @RequestParam(value="login") String login,
                              @RequestParam(value="password") String password) {
        User user = getUser(login);

        if(user.getPassword().equals(password)) {
            return contactRepo.findByIdAndUserLike(id, user);
        }
        else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/contacts")
    public Contact createContact(@RequestBody Contact contact,
                                 @RequestParam(value="login") String login,
                                 @RequestParam(value="password") String password,
                                 HttpServletResponse response) {
        User user = getUser(login);

        if(user.getPassword().equals(password)) {
            contact.setUser(user);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return contactRepo.save(contact);
        }
        else {
            throw new ForbiddenException();
        }
    }

    @PostMapping("/sign-in")
    public User registration(@RequestParam(value="login") String login,
                             @RequestParam(value="password") String password,
                             HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_CREATED);
        return userRepo.save(new User(login, password, LocalDateTime.now()));
    }

    @PutMapping("/contacts/{id}")
    public Contact updateContact(@PathVariable Long id,
                                 @RequestParam(value="login") String login,
                                 @RequestParam(value="password") String password,
                                 @RequestBody Contact newContact) {
        User user = getUser(login);

        if(user.getPassword().equals(password)) {
            Contact contact = contactRepo.findByIdAndUserLike(id, user);
            contact.SetContact(newContact);
            contact.setUser(user);
            return contactRepo.save(contact);
        }
        else {
            throw new ForbiddenException();
        }
    }

    @DeleteMapping("/contacts/{id}")
    public void deleteContact(@PathVariable Long id,
                              @RequestParam(value="login") String login,
                              @RequestParam(value="password") String password,
                              HttpServletResponse response) {
        User user = getUser(login);

        if(user.getPassword().equals(password)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            contactRepo.deleteById(id);
        }
        else {
            throw new ForbiddenException();
        }
    }

    private User getUser(String login) {
        return userRepo.findById(login).map(newuser -> {
            newuser.setLastvisit(LocalDateTime.now());
            return newuser;
        }).orElseThrow(ForbiddenException::new);
    }
}
