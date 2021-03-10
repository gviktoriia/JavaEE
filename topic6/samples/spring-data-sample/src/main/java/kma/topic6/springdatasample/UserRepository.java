package kma.topic6.springdatasample;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByEmail(String email);

    @Override
    UserEntity save(UserEntity user);

    Page<UserEntity> findAllByFirstNameEqualsAndLastNameEquals(String firstName, String lastName, Pageable pageable);
}
