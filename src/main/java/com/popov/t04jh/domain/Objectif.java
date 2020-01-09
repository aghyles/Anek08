package com.popov.t04jh.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Objectif.
 */
@Entity
@Table(name = "objectif")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Objectif implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "objectif_name", nullable = false)
    private String objectifName;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "objectif_exercice",
               joinColumns = @JoinColumn(name = "objectif_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "exercice_id", referencedColumnName = "id"))
    private Set<Exercice> exercices = new HashSet<>();

    @ManyToMany(mappedBy = "objectifs")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Theme> themes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectifName() {
        return objectifName;
    }

    public Objectif objectifName(String objectifName) {
        this.objectifName = objectifName;
        return this;
    }

    public void setObjectifName(String objectifName) {
        this.objectifName = objectifName;
    }

    public String getDescription() {
        return description;
    }

    public Objectif description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Exercice> getExercices() {
        return exercices;
    }

    public Objectif exercices(Set<Exercice> exercices) {
        this.exercices = exercices;
        return this;
    }

    public Objectif addExercice(Exercice exercice) {
        this.exercices.add(exercice);
        exercice.getObjectifs().add(this);
        return this;
    }

    public Objectif removeExercice(Exercice exercice) {
        this.exercices.remove(exercice);
        exercice.getObjectifs().remove(this);
        return this;
    }

    public void setExercices(Set<Exercice> exercices) {
        this.exercices = exercices;
    }

    public Set<Theme> getThemes() {
        return themes;
    }

    public Objectif themes(Set<Theme> themes) {
        this.themes = themes;
        return this;
    }

    public Objectif addTheme(Theme theme) {
        this.themes.add(theme);
        theme.getObjectifs().add(this);
        return this;
    }

    public Objectif removeTheme(Theme theme) {
        this.themes.remove(theme);
        theme.getObjectifs().remove(this);
        return this;
    }

    public void setThemes(Set<Theme> themes) {
        this.themes = themes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Objectif)) {
            return false;
        }
        return id != null && id.equals(((Objectif) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Objectif{" +
            "id=" + getId() +
            ", objectifName='" + getObjectifName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
