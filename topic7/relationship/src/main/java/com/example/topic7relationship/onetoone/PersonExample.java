package com.example.topic7relationship.onetoone;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonExample {

    private final EntityManager entityManager;

    @Transactional
    public long createPersonWithPassport(final String fullName, final String number, final String serial) {
        PassportEntity passport = PassportEntity.builder()
            .number(number)
            .serial(serial)
            .build();
        passport = entityManager.merge(passport);
        final PersonEntity person = PersonEntity.builder()
            .fullName(fullName)
            .passport(passport)
            .build();

        return entityManager.merge(person).getId();
    }

    @Transactional
    public long createPersonWithoutPassport(final String fullName) {
        final PersonEntity person = PersonEntity.builder()
            .fullName(fullName)
            .build();

        return entityManager.merge(person).getId();
    }

    @Transactional
    public PersonEntity getPersonById(final long id) {
        return entityManager.find(PersonEntity.class, id);
    }

    @Transactional
    public void deletePersonById(final long id) {
        entityManager.remove(entityManager.find(PersonEntity.class, id));
    }

}
