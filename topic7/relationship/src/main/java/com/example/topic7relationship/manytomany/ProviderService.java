package com.example.topic7relationship.manytomany;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProviderService {

    private final EntityManager entityManager;

    public ProviderEntity create(final String name) {
        return entityManager.merge(
            ProviderEntity.builder()
                .name(name)
                .build()
        );
    }

    public ProviderEntity update(final ProviderEntity supermarket) {
        return entityManager.merge(supermarket);
    }

    public ProviderEntity getById(final long id) {
        return entityManager.find(ProviderEntity.class, id);
    }

}
