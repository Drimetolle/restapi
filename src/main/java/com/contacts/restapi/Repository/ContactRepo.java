package com.contacts.restapi.Repository;

import com.contacts.restapi.domain.Contact;
import com.contacts.restapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, Long> {
    Contact findByIdAndUserLike(Long id, User user);
    List<Contact> findByUserLike(User user);
}
