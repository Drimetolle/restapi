package com.contacts.restapi.search;

import com.contacts.restapi.domain.Contact;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ContactSpecificationsBuilder {

    private List<SearchCriteria> params = new ArrayList<>();

    public ContactSpecificationsBuilder with(
            String key, Object value) {

        params.add(new SearchCriteria(key, value));

        return this;
    }

    public Specification<Contact> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification result = new ContactSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(new ContactSpecification(params.get(i)));
        }

        return result;
    }
}