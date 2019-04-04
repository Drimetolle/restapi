package com.contacts.restapi.Repository;

import com.contacts.restapi.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact, Long> {
}
