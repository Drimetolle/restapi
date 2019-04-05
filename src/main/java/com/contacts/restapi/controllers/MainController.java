package com.contacts.restapi.controllers;

import com.contacts.restapi.Repository.ContactRepo;
import com.contacts.restapi.Repository.UserRepo;
import com.contacts.restapi.domain.Contact;
import com.contacts.restapi.domain.User;
import com.contacts.restapi.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public List<Contact> getContacts() {
        return contactRepo.findAll();
    }

    @GetMapping("/contacts/{id}")
    public Contact getContact(@PathVariable Long id) {
        return contactRepo.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("/contacts")
    public Contact createContact(@RequestBody Contact contact, @RequestParam(value="login") String login, @RequestParam(value="password") String password) {
        User user = userRepo.findById(login).map(newuser -> {
            newuser.setLastvisit(LocalDateTime.now());
            return newuser;
        }).orElseThrow(NotFoundException::new);

        if(user.getPassword().equals(password)) {
            contact.setUser(user);
            return contactRepo.save(contact);
        }
        else {
            throw new NotFoundException();
        }
    }

    @PostMapping("/sign-in")
    public User registration(@RequestParam(value="login") String login, @RequestParam(value="password") String password) {
        return userRepo.save(new User(login, password, LocalDateTime.now()));
    }

    @PutMapping("/contacts/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact newContact) {
        return contactRepo.findById(id).map(
                contact -> {
                    contact.setFirstName(newContact.getFirstName());
                    contact.setSecondName(newContact.getSecondName());
                    contact.setEmail(newContact.getEmail());
                    contact.setPhoneNumber(newContact.getPhoneNumber());
                    return contactRepo.save(contact);
                })
                .orElseGet(() -> {
                    newContact.setId(id);
                    return contactRepo.save(newContact);
                });
    }

    @DeleteMapping("/contacts/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactRepo.deleteById(id);
    }
}
