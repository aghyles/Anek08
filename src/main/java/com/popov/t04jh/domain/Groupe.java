package com.popov.t04jh.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.popov.t04jh.domain.enumeration.Category;

/**
 * A Groupe.
 */
@Entity
@Table(name = "groupe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "groupe_name", nullable = false)
    private String groupeName;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @NotNull
    @Column(name = "days", nullable = false)
    private String days;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "saison")
    private String saison;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "obs")
    private String obs;

    @OneToMany(mappedBy = "groupe")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Swimer> swimers = new HashSet<>();

    @OneToMany(mappedBy = "groupe")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FicheSeance> ficheSeances = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupeName() {
        return groupeName;
    }

    public Groupe groupeName(String groupeName) {
        this.groupeName = groupeName;
        return this;
    }

    public void setGroupeName(String groupeName) {
        this.groupeName = groupeName;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Groupe startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public String getDays() {
        return days;
    }

    public Groupe days(String days) {
        this.days = days;
        return this;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Groupe endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getSaison() {
        return saison;
    }

    public Groupe saison(String saison) {
        this.saison = saison;
        return this;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public Category getCategory() {
        return category;
    }

    public Groupe category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getObs() {
        return obs;
    }

    public Groupe obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<Swimer> getSwimers() {
        return swimers;
    }

    public Groupe swimers(Set<Swimer> swimers) {
        this.swimers = swimers;
        return this;
    }

    public Groupe addSwimer(Swimer swimer) {
        this.swimers.add(swimer);
        swimer.setGroupe(this);
        return this;
    }

    public Groupe removeSwimer(Swimer swimer) {
        this.swimers.remove(swimer);
        swimer.setGroupe(null);
        return this;
    }

    public void setSwimers(Set<Swimer> swimers) {
        this.swimers = swimers;
    }

    public Set<FicheSeance> getFicheSeances() {
        return ficheSeances;
    }

    public Groupe ficheSeances(Set<FicheSeance> ficheSeances) {
        this.ficheSeances = ficheSeances;
        return this;
    }

    public Groupe addFicheSeance(FicheSeance ficheSeance) {
        this.ficheSeances.add(ficheSeance);
        ficheSeance.setGroupe(this);
        return this;
    }

    public Groupe removeFicheSeance(FicheSeance ficheSeance) {
        this.ficheSeances.remove(ficheSeance);
        ficheSeance.setGroupe(null);
        return this;
    }

    public void setFicheSeances(Set<FicheSeance> ficheSeances) {
        this.ficheSeances = ficheSeances;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Groupe)) {
            return false;
        }
        return id != null && id.equals(((Groupe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Groupe{" +
            "id=" + getId() +
            ", groupeName='" + getGroupeName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", days='" + getDays() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", saison='" + getSaison() + "'" +
            ", category='" + getCategory() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
