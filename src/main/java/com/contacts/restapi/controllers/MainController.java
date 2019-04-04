package com.contacts.restapi.controllers;

import com.contacts.restapi.Repository.ContactRepo;
import com.contacts.restapi.domain.Contact;
import com.contacts.restapi.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
public class MainController {
    private final ContactRepo contactRepo;

    @Autowired
    public MainController(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactRepo.findAll();
    }

    @GetMapping("{id}")
    public Contact getContact(@PathVariable Long id) {
        return contactRepo.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepo.save(contact);
    }

    @PutMapping("{id}")
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

    @DeleteMapping("{id}")
    public void deleteContact(@PathVariable Long id) {
        contactRepo.deleteById(id);
    }
}
