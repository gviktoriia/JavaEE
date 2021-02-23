package kma.topic6.springdatasample.embedded;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "billing")
@Getter
@Setter
@ToString(exclude = "apartment")
public class BillingEntity {

    @EmbeddedId
    private ApartmentBillingId id;

    @Column(name = "bill")
    private double bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", insertable = false, updatable = false)
    private ApartmentEntity apartment;

}
