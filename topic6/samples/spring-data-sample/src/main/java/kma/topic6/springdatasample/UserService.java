package kma.topic6.springdatasample;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kma.topic6.springdatasample.embedded.ApartmentService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EntityManager entityManager;
    private final ApartmentService apartmentService;

    @Transactional
    public UserEntity createUser(String firstName, String lastName, String email, String number) {
        UserEntity user = new UserEntity();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        UserEntity entity = entityManager.merge(user);
        apartmentService.createApartment(number);
        return entity;
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
        try {
            return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class)
                .setParameter("email", email)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
