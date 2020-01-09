package com.popov.t04jh.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Presence.
 */
@Entity
@Table(name = "presence")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Presence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_nageur_abs")
    private String idNageurAbs;

    @ManyToOne
    @JsonIgnoreProperties("presences")
    private FicheSeance ficheSeance;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNageurAbs() {
        return idNageurAbs;
    }

    public Presence idNageurAbs(String idNageurAbs) {
        this.idNageurAbs = idNageurAbs;
        return this;
    }

    public void setIdNageurAbs(String idNageurAbs) {
        this.idNageurAbs = idNageurAbs;
    }

    public FicheSeance getFicheSeance() {
        return ficheSeance;
    }

    public Presence ficheSeance(FicheSeance ficheSeance) {
        this.ficheSeance = ficheSeance;
        return this;
    }

    public void setFicheSeance(FicheSeance ficheSeance) {
        this.ficheSeance = ficheSeance;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Presence)) {
            return false;
        }
        return id != null && id.equals(((Presence) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Presence{" +
            "id=" + getId() +
            ", idNageurAbs='" + getIdNageurAbs() + "'" +
            "}";
    }
}
