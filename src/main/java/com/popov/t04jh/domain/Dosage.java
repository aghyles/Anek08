package com.popov.t04jh.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Dosage.
 */
@Entity
@Table(name = "dosage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dosage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "dosage_s", nullable = false)
    private String dosageS;

    @NotNull
    @Column(name = "dosagelong", nullable = false)
    private Long dosagelong;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDosageS() {
        return dosageS;
    }

    public Dosage dosageS(String dosageS) {
        this.dosageS = dosageS;
        return this;
    }

    public void setDosageS(String dosageS) {
        this.dosageS = dosageS;
    }

    public Long getDosagelong() {
        return dosagelong;
    }

    public Dosage dosagelong(Long dosagelong) {
        this.dosagelong = dosagelong;
        return this;
    }

    public void setDosagelong(Long dosagelong) {
        this.dosagelong = dosagelong;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dosage)) {
            return false;
        }
        return id != null && id.equals(((Dosage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Dosage{" +
            "id=" + getId() +
            ", dosageS='" + getDosageS() + "'" +
            ", dosagelong=" + getDosagelong() +
            "}";
    }
}
