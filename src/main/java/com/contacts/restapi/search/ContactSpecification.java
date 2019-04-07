package com.contacts.restapi.search;

import com.contacts.restapi.domain.Contact;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ContactSpecification implements Specification<Contact> {
    private SearchCriteria criteria;

    public ContactSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(
            Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.equal(root.get(criteria.getKey()), criteria.getValue());
    }
}
