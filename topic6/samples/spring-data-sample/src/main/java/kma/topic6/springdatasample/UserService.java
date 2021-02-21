package kma.topic6.springdatasample;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EntityManager entityManager;

    @Transactional
    public UserEntity createUser(String firstName, String lastName, String email) {
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        return entityManager.merge(user);
    }

    @Transactional
    public UserEntity getUserById(int id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Transactional
    public List<UserEntity> findAllUsers() {
        return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class)
            .getResultList();
    }

    @Transactional
    public long countUsers() {
        return entityManager.createQuery("SELECT COUNT(u) FROM UserEntity u", Long.class)
            .getSingleResult();
    }

    @Transactional
    public List<UserEntity> findUserWhereFirstOrLastNameContains(String searchText) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.firstName LIKE :query OR u.lastName LIKE :query", UserEntity.class)
            .setParameter("query", '%' + searchText + '%')
            .getResultList();
    }

    public UserEntity findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class)
            .setParameter("email", email)
            .getSingleResult();
    }

}
