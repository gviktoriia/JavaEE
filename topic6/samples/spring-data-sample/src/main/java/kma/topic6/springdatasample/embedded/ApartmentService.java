package kma.topic6.springdatasample.embedded;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final EntityManager entityManager;

    @Transactional
    public int createApartment(final String apartmentNumber) {
        ApartmentEntity apartmentEntity = new ApartmentEntity();
        apartmentEntity.setNumber(apartmentNumber);

        ApartmentEntity savedApartment = entityManager.merge(apartmentEntity);
        return savedApartment.getId();
    }

    @Transactional
    public List<ApartmentEntity> findAllApartmentsWithoutFetch() {
        return entityManager.createQuery("SELECT a FROM ApartmentEntity a", ApartmentEntity.class)
            .getResultList();

    }

    @Transactional
    public List<ApartmentEntity> findAllApartmentsWithFetch() {
        return entityManager.createQuery("SELECT DISTINCT a FROM ApartmentEntity a LEFT JOIN FETCH a.billings", ApartmentEntity.class)
            .getResultList();

    }

    @Transactional
    public void createBilling(int apartmentId, LocalDate period, double bill) {
        BillingEntity billingEntity = new BillingEntity();
        billingEntity.setId(ApartmentBillingId.of(apartmentId, period));
        billingEntity.setBill(bill);

        entityManager.merge(billingEntity);
    }

    @Transactional
    public BillingEntity getBillingById(ApartmentBillingId apartmentBillingId) {
        return entityManager.find(BillingEntity.class, apartmentBillingId);
    }

    @Transactional
    public BillingEntity getBillingByIdWithFetch(ApartmentBillingId apartmentBillingId) {
        return entityManager.createQuery(
            "select b from BillingEntity b "
                + "join fetch b.apartment apartment "
                + "join fetch apartment.billings "
                + "where b.id = :id ",
            BillingEntity.class
        )
            .setParameter("id", apartmentBillingId)
            .getSingleResult();
    }

}
