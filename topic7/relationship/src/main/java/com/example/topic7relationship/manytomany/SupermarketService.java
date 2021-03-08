package com.example.topic7relationship.manytomany;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SupermarketService {

    private final EntityManager entityManager;

    public SupermarketEntity create(final String name) {
        return entityManager.merge(
            SupermarketEntity.builder()
                .name(name)
                .build()
        );
    }

    public SupermarketEntity update(final SupermarketEntity supermarket) {
        return entityManager.merge(supermarket);
    }

    public SupermarketEntity getById(final long id) {
        return entityManager.find(SupermarketEntity.class, id);
    }

    public void deleteById(final long id) {
        entityManager.remove(getById(id));
    }

}
